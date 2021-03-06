package com.pieces.service.shiro;

import java.io.Serializable;
import java.util.Collection;

import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.tools.utils.SpringUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pieces.service.enums.RedisEnum;
import com.pieces.service.redis.RedisManager;
import com.pieces.service.utils.SerializeUtils;

public class RedisSessionDAO extends AbstractSessionDAO {

	private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
	/**
	 * shiro-redis的session对象前缀
	 */
	private RedisManager redisManager;
	
	/**
	 * The Redis key prefix for the sessions 
	 * 使用spring注入，每个项目一个不同的前缀。防止相同窗口访问不同项目造成key值相同，导致覆盖
	 */
	private String keyPrefix;// = RedisEnum.KEY_PREFIX_SHIRO_REDIS_SESSION.getValue();
	
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}

	@Override
	public void delete(Session session) {
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return;
		}
		redisManager.del(this.getByteKey(session.getId()));

	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);  
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			logger.error("session id is null");
			return null;
		}
		
		Session s = (Session)SerializeUtils.deserialize(redisManager.get(this.getByteKey(sessionId)));
		if (s!=null) {
			User user = (User) s.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
			if (user != null && user.getType() == 1 && user.getCertifyStatus() == 0) {
				UserService userService = (UserService) SpringUtil.getBean(UserService.class);
				user = userService.findById(user.getId());
				user.setPassword(null);
				user.setSalt(null);
				s.setAttribute(RedisEnum.USER_SESSION_BIZ.getValue(), user);
			}
		}
		return s;
	}
	
	
	
	/**
	 * 僅僅schedulor的時候用，最好不用
	 */
	@Override
	public Collection<Session> getActiveSessions() {
		throw new UnsupportedOperationException();
//		Set<Session> sessions = new HashSet<Session>();
//		Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
//		if(keys != null && keys.size()>0){
//			for(byte[] key:keys){
//				Session s = (Session)SerializeUtils.deserialize(redisManager.get(key));
//				sessions.add(s);
//			}
//		}
//		return sessions;
	}
	
	/**
	 * save session
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException{
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return;
		}
		
		byte[] key = getByteKey(session.getId());
		byte[] value = SerializeUtils.serialize(session);
		session.setTimeout(redisManager.getExpire()*1000);		
		this.redisManager.set(key, value, redisManager.getExpire());
	}
	
	
	/**
	 * 获得byte[]型的key
	 * @param sessionId
	 * @return
	 */
	private byte[] getByteKey(Serializable sessionId){
		String preKey = this.keyPrefix + sessionId;
		return preKey.getBytes();
	}
	
	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	/**
	 * Returns the Redis session keys
	 * prefix.
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key 
	 * prefix.
	 * @param keyPrefix The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	
	
}
