package com.jzt.passport.cas;

import java.util.Collection;

import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.registry.AbstractDistributedTicketRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.redis.RedisManager;
import com.jointown.zy.common.util.SerializeUtils;

/**
 * 基于redis的分布式ticket保存方案
 * @author LiuPiao
 *
 */
public class JztRedisTicketRegistry extends AbstractDistributedTicketRegistry {
	Logger log = LoggerFactory.getLogger(JztRedisTicketRegistry.class);
	
	@Autowired
	private RedisManager redisManager;
	private String stTime = "60";  //ST最大空闲时间 ,单位是秒
	private String tgtTime = "28800"; //TGT最大空闲时间   ,单位是秒
	
	@Override
	public void addTicket(Ticket ticket) {
		saveTicket(ticket); 
		log.debug("#########addTicket, Ticket IS:"+redisManager.getStringKey(ticket.getId(), RedisEnum.KEY_PREFIX_CAS_TICKETS));
	}

	@Override
	public Ticket getTicket(String ticketId) {
		return getProxiedTicketInstance(getRawTicket(ticketId)); 
	}

	@Override
	public boolean deleteTicket(String ticketId) {
		if (ticketId == null) {  
            return false;  
        }  
        redisManager.del(redisManager.getByteKey(ticketId, RedisEnum.KEY_PREFIX_CAS_TICKETS));
        log.debug("#########deleteTicket, Ticket IS:"+redisManager.getStringKey(ticketId, RedisEnum.KEY_PREFIX_CAS_TICKETS));
        return true;         
	}

	@Override
	public Collection<Ticket> getTickets() {
		throw new UnsupportedOperationException("GetTickets not supported.");  
	}

	@Override
	protected void updateTicket(Ticket ticket) {
		saveTicket(ticket); 
		log.debug("#########updateTicket, Ticket IS:"+redisManager.getStringKey(ticket.getId(), RedisEnum.KEY_PREFIX_CAS_TICKETS));
	}

	@Override
	protected boolean needsCallback() {
		return false;
	}
	
	
	public Ticket getRawTicket(final String ticketId) {  
        if(null == ticketId) {
        	return null; 
        }
        return (Ticket)SerializeUtils.deserialize(redisManager.get(redisManager.getByteKey(ticketId, RedisEnum.KEY_PREFIX_CAS_TICKETS)));
    }
	
	private void saveTicket(Ticket ticket) {
        int seconds = 0;  
        String key = ticket.getId() ;  
        if(ticket instanceof TicketGrantingTicket){  
            seconds = Integer.parseInt(tgtTime);  
        }else{  
            seconds = Integer.parseInt(stTime);  
        }  
        byte[] ticketBytes = SerializeUtils.serialize(ticket);
        redisManager.set(redisManager.getByteKey(key, RedisEnum.KEY_PREFIX_CAS_TICKETS), ticketBytes, seconds);
	}
	
	

	public String getStTime() {
		return stTime;
	}

	public void setStTime(String stTime) {
		this.stTime = stTime;
	}

	public String getTgtTime() {
		return tgtTime;
	}

	public void setTgtTime(String tgtTime) {
		this.tgtTime = tgtTime;
	} 

}
