package com.shop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class User implements Serializable{
	    
		private String username;
		private String userpass;
		private boolean isLogin;
		
		//注册
		public void reg(){     
				System.out.println();
				System.out.println("****欢迎注册！****");
				String username = null;
				boolean dis = true;
				while(dis){
					System.out.print("请输入用户名：");
					username = Shop.scan.next();
					if(FindUser(username)!=null)
					{
						System.out.println();
						System.out.println("*****用户名被占用了，换一个吧！*****");
						dis = false;
					}
					if(dis){
						break;
					}else{
					    dis = true;
					}
					
				}
				while(true){
					System.out.print("请输入密码：");
					String password = Shop.scan.next();
					System.out.print("请再次输入密码：");
					String repassword = Shop.scan.next();
					if(password.equals(repassword)){
						User newuser = new User(username,password);
						Shop.userList.add(newuser);
						Shop.userGood.put(newuser, Shop.mygoodList);
						break;
					}else{
						System.out.println("两次密码不一致，请重新输入！");
					}
				}
				isLogin = true;
				System.out.println();
				System.out.println("****恭喜你~注册成功！****");
				
				
		}
	    //登录
		public void login(){
			System.out.println();
			System.out.println("****欢迎登录！****");
			System.out.print("请输入用户名：");
			String login_username = Shop.scan.next();
			System.out.print("请输入密码：");
			String login_password = Shop.scan.next();
			if(FindUser(login_username)!=null){
				if(FindUser(login_username).getUserpass().equals(login_password)){
					Shop.onlineuser = new User(login_username,login_password);
					Shop.onlineuser.setIsLogin(true);
					System.out.println();
					System.out.println("****锵锵锵~登录成功！****");
				}else{
					System.out.println();
					System.out.println("****呜呜呜~你输入的密码不正确！****");
				}
			}
			else{
				System.out.println();
				System.out.println("****呜呜呜~你还没有注册呢！****");
			}
		}
		//显示商品（继承）
		public void showGood(){
			System.out.println();
			System.out.println("****所有商品****");
			if(Shop.goodList.size() == 0){
				System.out.println("现在商城还没有商品！");
			}else{
				for(int i=0;i<Shop.goodList.size();i++){
					Shop.goodList.get(i).showgood();
				}
				if(Shop.onlineuser.getIsLogin()){
					Shop.onlineuser.buy();
					Shop.onlineuser.save();
				}else{
					System.out.println("请先登录再购买商品！");
				}
			}
		}
		//保存购物车
		public void save(){
			if(Shop.onlineuser.getIsLogin()){
				Set<User> keyuser = Shop.userGood.keySet();
				Iterator<User> ite = keyuser.iterator();
				boolean iskey = false;
				int num;
				// 查找用户的购物车
				for(User user:keyuser){
					if(ite.hasNext()){
						User nowuser =ite.next();
						String nowname = (String)nowuser.getUsername();
						if(Shop.onlineuser.getUsername().equals(nowname)){
							List<Good> mygoodlist = Shop.userGood.get(nowuser);
							Shop.userGood.remove(Shop.onlineuser);
							Shop.userGood.put(Shop.onlineuser, mygoodlist);
							iskey = true;
							break;
						}
					}
				}
					if(!iskey){
						Shop.userGood.put(Shop.onlineuser, Shop.mygoodList);
					}
				}
				
		}
		//显示购物车
		public void showMyGood(){
			if(Shop.onlineuser.getIsLogin()){
				Set<User> keyuser = Shop.userGood.keySet();
				Iterator<User> ite = keyuser.iterator();
				boolean iskey = false;
				float Sumprice = 0;
				float oneprice = 0;
				int num;
				// 查找用户的购物车
				for(User user:keyuser){
					if(ite.hasNext()){
						User nowuser =ite.next();
						String nowname = (String)nowuser.getUsername();
						if(Shop.onlineuser.getUsername().equals(nowname)){
							System.out.println();
							System.out.println("*****购物车*****");
							List<Good> mygoodList = Shop.userGood.get(nowuser);
							for(int i = 0;i < mygoodList.size();i++ ){
								mygoodList.get(i).showgood();
								oneprice = mygoodList.get(i).getPrice();
								num = mygoodList.get(i).getNum();
								Sumprice += num*oneprice;
								iskey = true;	
							}
							System.out.println("所有宝贝共计"+Sumprice+"元");
							break;
						}
					}
				}
				if(!iskey){
					System.out.println();
					System.out.println("*****购物车里还没有内容！*****");
				}
				
			}else{
				System.out.println();
				System.out.println("*****请先登录在查看购物车！*****");
			}
		}
		//退出保存购物车
		public void back(){
			if(this.getIsLogin()){
				Set<User> keyname = Shop.userGood.keySet();
				Iterator<User> ite = keyname.iterator();
				boolean iskey = false;
				// 保存用户的购物车
				for(User username:keyname){
					if(ite.hasNext()){
						if(Shop.onlineuser.getUsername().equals(Shop.userGood.get(ite.next()))){
							Shop.userGood.remove(Shop.onlineuser.getUsername());
							Shop.userGood.put(Shop.onlineuser,Shop.mygoodList);
							iskey = true;
						}
					}
				}
				if(iskey){
					Shop.userGood.put(Shop.onlineuser,Shop.mygoodList);
				}
				Shop.onlineuser.setIsLogin(false);
			}
				
		}
		//查找用户
		public User FindUser(String name){
				for(int i=0;i<Shop.userList.size();i++){
					if(name.equals(Shop.userList.get(i).getUsername())){
						return Shop.userList.get(i);
					}
				}
				return null;
			
		}
        //查找商品
		public Good FindGood(String id){
			Good nowgood = null;
			for(int i=0;i<Shop.goodList.size();i++){
				nowgood = Shop.goodList.get(i);
				if(id.equals(nowgood.getId())){
					return nowgood;
				}
			}
			return null;
		}
		//购买
		public void buy(){
			System.out.println();
			System.out.println("****购买商品****");
			System.out.print("请输入商品编号(不想购买请输入N)：");
			String id = Shop.scan.next();
		    if(id.equals("N") || id.equals("n")){
					
			}else if(FindGood(id)!=null)
			{
					System.out.print("请输入购买数量：");
					int num= Shop.scan.nextInt();
					float price = FindGood(id).getPrice();
					String name = FindGood(id).getName();
					Good mygood = new Good(id,name,price,num);
					FindGood(id).setNum((FindGood(id).getNum()-num));
					Shop.mygoodList.add(mygood);
				
			}else{
				System.out.println("");
				System.out.println("****你要购买的商品不存在！****");
			}
			
			
		}
		User(){
			
		}
		User(String username,String userpass){
			this.username = username;
			this.userpass = userpass;
		}
		
		public void setIsLogin(boolean isLogin){
			this.isLogin = isLogin;
		}

		public boolean getIsLogin(){
			return isLogin;
		}
		
		public void setUsername(String username){
			this.username = username;
		}
		
		public void setUserpass(String userpass){
			this.userpass = userpass;
		}
		
		public String getUsername(){
			return username;
		}
		
		public String getUserpass(){
			return userpass;
		}
		
		public void showUser(){
			System.out.println("用户名："+username + "  密码：" + userpass);
		}
}
