package com.xqr.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class PhoneCode {
    public static void main(String[] args) {
       //模拟验证码调用
        String code=verifyCode("15282097256");
        //getresgide("15282097256",code);
    }

    //数字验证码
    public static String getCode(){
        Random random = new Random();
        String Code="";
        for (int i = 0; i < 6; i++) {
            int rand=random.nextInt(10);
            Code+=rand;
        }
        System.out.println(Code);
        return Code;
    }

    //让手机只能发送三次，放入redis中，设置到期时间
    public static String verifyCode(String phone){
        //链接redis
        Jedis jedis = new Jedis("1.117.217.16",6379);
        jedis.auth("x744713157");
        //凭借key
        //手机发送次数
        String countKey="VerifyCode"+phone+":count";
        //验证码key
        String codeKey="VerfyCode"+phone+":Code";
        //每个手机每天只能发送三次
        String count=jedis.get(countKey);
        if(count==null){
            jedis.setex(countKey,24*60*60,"1");
        }else if (Integer.parseInt(count)<=2){
            jedis.incr(countKey);
        }else if (Integer.parseInt(count)>2){
            System.out.println("今天的发送次数超过3次");
            jedis.close();
            return "今天的发送次数超过3次";
        }
        //发送的验证码存在redis中
        String vcode=getCode();
        jedis.setex(codeKey,120,vcode);
        jedis.close();
        return vcode;
    }

    //验证码校验
    public static void getresgide(String phone,String Code){
        //获取验证码
        Jedis jedis = new Jedis("1.117.217.16",6379);
        jedis.auth("x744713157");
        String codeKey="VerfyCode"+phone+":Code";
        String redisCode = jedis.get(codeKey);
        //判断
        if (redisCode.equals(Code)){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
        jedis.close();
    }
}
