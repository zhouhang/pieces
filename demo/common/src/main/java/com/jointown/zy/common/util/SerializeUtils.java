package com.jointown.zy.common.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.jointown.zy.common.redis.RedisEnum;

import de.javakaffee.kryoserializers.ArraysAsListSerializer;
import de.javakaffee.kryoserializers.BitSetSerializer;
import de.javakaffee.kryoserializers.CollectionsEmptyListSerializer;
import de.javakaffee.kryoserializers.CollectionsEmptyMapSerializer;
import de.javakaffee.kryoserializers.CollectionsEmptySetSerializer;
import de.javakaffee.kryoserializers.CollectionsSingletonListSerializer;
import de.javakaffee.kryoserializers.CollectionsSingletonMapSerializer;
import de.javakaffee.kryoserializers.CollectionsSingletonSetSerializer;
import de.javakaffee.kryoserializers.CopyForIterateCollectionSerializer;
import de.javakaffee.kryoserializers.CopyForIterateMapSerializer;
import de.javakaffee.kryoserializers.DateSerializer;
import de.javakaffee.kryoserializers.EnumMapSerializer;
import de.javakaffee.kryoserializers.EnumSetSerializer;
import de.javakaffee.kryoserializers.GregorianCalendarSerializer;
import de.javakaffee.kryoserializers.JdkProxySerializer;
import de.javakaffee.kryoserializers.KryoReflectionFactorySupport;
import de.javakaffee.kryoserializers.RegexSerializer;
import de.javakaffee.kryoserializers.SynchronizedCollectionsSerializer;
import de.javakaffee.kryoserializers.URISerializer;
import de.javakaffee.kryoserializers.UUIDSerializer;
import de.javakaffee.kryoserializers.UnmodifiableCollectionsSerializer;


public class SerializeUtils {
	private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);
	//序列化策略接口
	private static Serializer<Object> jdkSerz = new BuildInSerializer();
	
	private static Serializer<String> stringSerz = new StringSerializer();
	
	private static Serializer<Object> getDefaultSerializer(){
		return jdkSerz;
	}
	
	public static Serializer<String> getStringSerializer(){
		return stringSerz;
	}
	
	
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {
		return getDefaultSerializer().deserialize(bytes);
	}
	
	/**
	 * 
	 * @Description: 指定序列化器的反序列化
	 * @Author: robin.liu
	 * @Date: 2015年12月17日
	 * @param ser
	 * @param bytes
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object deserialize(Serializer ser,byte[] bytes) {
		return ser.deserialize(bytes);
	}
	
	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	public static <T> byte[] serialize(T object) {
		return getDefaultSerializer().serialize(object);
	}
	
	/**
	 * 
	 * @Description: 指定序列化器的序列化
	 * @Author: robin.liu
	 * @Date: 2015年12月17日
	 * @param ser
	 * @param object
	 * @return
	 */
	public static <T> byte[] serialize(Serializer<T> ser,T object) {
		return ser.serialize(object);
	}
	
	
	/********************************************************用于抽象化序列化机制，扩展才用，不扩展不用*******************************************************
	 *****************************************************************************************************************************************/
	
	
	/**
	 * 
	 * @author LiuPiao
	 * 序列化抽象类，用于封装抽象方法
	 *
	 */
	public static abstract class Serializer<T> {
		abstract public byte[] doSerialize(T object);
		abstract public T doDeserialize(byte[] bytes);
		
		public byte[] serialize(T object){
			if (object == null) {
				return new byte[0];
			}
			return doSerialize(object);
		}
		
		public Object deserialize(byte[] bytes){
			if(ArrayUtils.isEmpty(bytes)){
				return null;
			}
			return doDeserialize(bytes);
		}
	}
	
	/**
	 * 私有类用于java原生的序列化机制
	 * @author LiuPiao
	 *
	 */
	private static class BuildInSerializer extends Serializer<Object>{
		@Override
		public byte[] doSerialize(Object object) {
			byte[] result = null;
			ObjectOutputStream objectOutputStream = null;
			try {
				ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
				try  {
					if (!(object instanceof Serializable)) {
						throw new IllegalArgumentException(SerializeUtils.class.getSimpleName() + " requires a Serializable payload " +
								"but received an object of type [" + object.getClass().getName() + "]");
					}
					objectOutputStream = new ObjectOutputStream(byteStream);
					objectOutputStream.writeObject(object);
					objectOutputStream.flush();
					result =  byteStream.toByteArray();
				}
				catch (Throwable ex) {
					throw new Exception("Failed to serialize", ex);
				}
			} catch (Exception ex) {
				logger.error("Failed to serialize",ex);
			}finally{
				if(null!=objectOutputStream)
					try {
						objectOutputStream.close();
					} catch (IOException e) {
						logger.error("ObjectOutputStream closing error when doSerialize.");  
					}
			}
			return result;
		}

		@Override
		public Object doDeserialize(byte[] bytes) {
			Object result = null;
			ObjectInputStream objectInputStream = null;
			try {
				ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
				try {
					objectInputStream = new ObjectInputStream(byteStream);
					try {
						result = objectInputStream.readObject();
					}
					catch (ClassNotFoundException ex) {
						throw new Exception("Failed to deserialize object type", ex);
					}
				}
				catch (Throwable ex) {
					throw new Exception("Failed to deserialize", ex);
				}
			} catch (Exception e) {
				logger.error("Failed to deserialize",e);
			}finally{
				if(null!=objectInputStream)
					try {
						objectInputStream.close();
					} catch (IOException e) {
						logger.error("objectInputStream closing error when doDeserialize.");  
					}
			}
			return result;
		}
		
	}
	
	private static class StringSerializer extends Serializer<String>{
		
		private static final String DEFAULT_CHARSET = "UTF-8";

		@Override
		public byte[] doSerialize(String object) {
			try {
				return object.getBytes(DEFAULT_CHARSET);
			} catch (UnsupportedEncodingException e) {
				logger.error("Failed to deserialize",e);
			}  
			return null;
		}

		@Override
		public String doDeserialize(byte[] bytes) {
			try {
				return new String(bytes,DEFAULT_CHARSET);
			} catch (UnsupportedEncodingException e) {
				logger.error("Failed to deserialize",e);
			}
			return null;
		}
		
	}
	
	/**
	 * 私有类，用于实现kryo的高效序列化机制.
	 * 基于Kryo 2.x的(序列化/反序列化)类.可以处理任何类(包括没有实现Serializable接口的类).
	 * @author LiuPiao
	 *
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private static class KryoSerializer extends Serializer{
		private static final ThreadLocal<Kryo> _threadLocalKryo = new ThreadLocal<Kryo>() {
		   protected Kryo initialValue() {
		    	Kryo kryo = new KryoReflectionFactorySupport() {

					@Override
					@SuppressWarnings({ "unchecked" })
					public com.esotericsoftware.kryo.Serializer<?> getDefaultSerializer(
							final Class type) {
						if (EnumSet.class.isAssignableFrom(type)) {
							return new EnumSetSerializer();
						}
						if (EnumMap.class.isAssignableFrom(type)) {
							return new EnumMapSerializer();
						}
						if (Collection.class.isAssignableFrom(type)) {
							return new CopyForIterateCollectionSerializer();
						}
						if (Map.class.isAssignableFrom(type)) {
							return new CopyForIterateMapSerializer();
						}
						if (Date.class.isAssignableFrom(type)) {
							return new DateSerializer(type);
						}

						// 判断是否是EnhancerByCGLIB
						try {
							if (type.getName().indexOf("$$EnhancerByCGLIB$$") > 0) {
								Method method = Class
										.forName(
												"de.javakaffee.kryoserializers.cglib.CGLibProxySerializer")
										.getDeclaredMethod("canSerialize",
												Class.class);
								if ((Boolean) method.invoke(null, type)) {
									return getSerializer(Class
											.forName("de.javakaffee.kryoserializers.cglib.CGLibProxySerializer$CGLibProxyMarker"));
								}
							}
						} catch (Throwable thex) {
							logger.error(thex.getMessage());
						}

						return super.getDefaultSerializer(type);
					}
		    	};//end of KryoReflectionFactorySupport class

		      kryo.setClassLoader(Thread.currentThread().getContextClassLoader());
		      kryo.setRegistrationRequired(false);

		      kryo.register(Arrays.asList("").getClass(), new ArraysAsListSerializer());
		      kryo.register(Collections.EMPTY_LIST.getClass(), new CollectionsEmptyListSerializer());
		      kryo.register(Collections.EMPTY_MAP.getClass(), new CollectionsEmptyMapSerializer());
		      kryo.register(Collections.EMPTY_SET.getClass(), new CollectionsEmptySetSerializer());
		      kryo.register(Collections.singletonList("").getClass(), new CollectionsSingletonListSerializer());
		      kryo.register(Collections.singleton("").getClass(), new CollectionsSingletonSetSerializer());
		      kryo.register(Collections.singletonMap("", "").getClass(), new CollectionsSingletonMapSerializer());
		      kryo.register(BigDecimal.class, new DefaultSerializers.BigDecimalSerializer());
		      kryo.register(BigInteger.class, new DefaultSerializers.BigIntegerSerializer());
		      kryo.register( Pattern.class, new RegexSerializer() );
		      kryo.register( BitSet.class, new BitSetSerializer() );
		      kryo.register( URI.class, new URISerializer() );
		      kryo.register( UUID.class, new UUIDSerializer() );
		      kryo.register(GregorianCalendar.class, new GregorianCalendarSerializer());
		      kryo.register(InvocationHandler.class, new JdkProxySerializer());

		      UnmodifiableCollectionsSerializer.registerSerializers(kryo);
		      SynchronizedCollectionsSerializer.registerSerializers(kryo);

		      //动态注册JodaDateTimeSerializer
		      try {
		        Class<?> clazz = Class.forName("org.joda.time.DateTime");
		        com.esotericsoftware.kryo.Serializer<?> serializer = (com.esotericsoftware.kryo.Serializer<?>) Class.forName("de.javakaffee.kryoserializers.jodatime.JodaDateTimeSerializer").newInstance();
		        kryo.register(clazz, serializer);
		      } catch (Throwable thex) {
		        //thex.printStackTrace();
		      }

		      //动态注册CGLibProxySerializer
		      try {
		        Class<?> clazz = Class.forName("de.javakaffee.kryoserializers.cglib.CGLibProxySerializer$CGLibProxyMarker");
		        com.esotericsoftware.kryo.Serializer<?> serializer = (com.esotericsoftware.kryo.Serializer<?>) Class.forName("de.javakaffee.kryoserializers.cglib.CGLibProxySerializer").newInstance();
		        kryo.register(clazz, serializer);
		      } catch (Throwable thex) {
		        //thex.printStackTrace();
		      }

		      return kryo;
		   }//end of initialize method
		};//end of ThreadLocal class
		
		private KryoSerializer() {}

		@Override
		public byte[] doSerialize(Object object) {
			Kryo kryo = _threadLocalKryo.get();
		    Output output = new Output(1024, -1);
		    kryo.writeClassAndObject(output, object);
		    return output.toBytes();
		}

		@Override
		public Object doDeserialize(byte[] bytes) {
			Kryo kryo = _threadLocalKryo.get();
		    Input input = new Input(bytes);
		    return kryo.readClassAndObject(input);
		}
		
	}
	
	
	public static Session fillSession(){
		SimpleSession session = new SimpleSession("10.0.2.24");
		Date d = new Date();
		session.setId(RedisEnum.KEY_PREFIX_SHIRO_REDIS_SESSION.getValue()+UUID.randomUUID().toString());
		session.setLastAccessTime(d);
		session.setTimeout(300000);
		session.setExpired(false);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("test", "testvalue");
		session.setAttributes(map);
		return session;
	}
	
	public static Session fillTestSession(){
		TestSession session = new TestSession();
		session.setId(RedisEnum.KEY_PREFIX_SHIRO_REDIS_SESSION.getValue()+UUID.randomUUID().toString());
		return session;
	}
	
	private static class TestSession implements Session{
		
		private transient Serializable id;
		
		public void setId(Serializable id){
			this.id = id;
		}

		@Override
		public Serializable getId() {
			return id;
		}

		@Override
		public Date getStartTimestamp() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Date getLastAccessTime() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getTimeout() throws InvalidSessionException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setTimeout(long maxIdleTimeInMillis)
				throws InvalidSessionException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getHost() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void touch() throws InvalidSessionException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void stop() throws InvalidSessionException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Collection<Object> getAttributeKeys()
				throws InvalidSessionException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getAttribute(Object key) throws InvalidSessionException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setAttribute(Object key, Object value)
				throws InvalidSessionException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object removeAttribute(Object key)
				throws InvalidSessionException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public static void main(String[] args) {
//		Session source = fillSession();
//		byte[] sObj = SerializeUtils.serialize(source);
//		Session dsObj = (Session)SerializeUtils.deserialize(sObj);
//		System.out.println("序列化后还原的session，id是："+dsObj.getId());
		
		String source = "liupiao";
		byte[] sObj = SerializeUtils.serialize(getStringSerializer(), source);
		Object dsObj = SerializeUtils.deserialize(getStringSerializer(),sObj);
		System.out.println("序列化后还原的session，id是："+dsObj);
	}
}
