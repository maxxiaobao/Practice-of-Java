package com.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
	List<Good> goodlist = new ArrayList<Good>();
	List<User> userlist = new ArrayList<User>();
	Scanner scan = new Scanner(System.in);
	
	//主函数
	public static void main(String[] args) {   
		Console cs = new Console();
		cs.Start();
	}
	
	//开始入口
    private void Start(){        
    	boolean go_on = true;
    	while(go_on){
    		//菜单显示
    		this.ShowMenu();
        	int n = scan.nextInt();
        	//用户选择
        	go_on = this.choice(n);
        }
    }
	
	private void ShowMenu(){  
		System.out.println();
		System.out.println("*****欢迎来到购物商城*****");
    	System.out.println("\t1.用户注册");
    	System.out.println("\t2.用户登录");
    	System.out.println("\t3.查看商城");
    	System.out.println("\t4.购物车");
    	System.out.println("\t5.管理员登录");
    	System.out.println("\t6.退出");
    	System.out.println("************************");
    	System.out.print("请输入您的选择：");
	}
	
	private boolean choice(int n){    
		switch (n){
    	case 1:
    		this.reg();
    		break;
    	case 2:
    		this.login();
    		break;
    	case 3:
    		showGood();
    		break;
    	case 4:
    		System.out.println("选择了4");
    		break;
    	case 5:
    		this.adminlogin();
    		break;
    	case 6:
    		System.out.println("选择了6，程序已退出！");
    		return false;
    	default:
    		System.out.println("选择有误，请重新输入！");
    		break;
    	}
		return true;
	}
	
	private void reg(){
		System.out.println();
		System.out.println("****欢迎注册！****");
		String username = null;
		boolean dis = true;
		while(dis){
			System.out.print("请输入用户名：");
			username = scan.next();
			for(int i= 0;i<userlist.size();i++){
				String nowusername = userlist.get(i).getUsername();
				if(username.equals(nowusername)){
					System.out.println();
					System.out.println("*****用户名被占用了，换一个吧！*****");
					dis = false;
					break;
					}
			}
			if(dis == true){
				break;
			}else{
				dis = true;
			}
			
		}
		while(true){
			System.out.print("请输入密码：");
			String password = scan.next();
			System.out.print("请再次输入密码：");
			String repassword = scan.next();
			if(password.equals(repassword)){
				User newuser = new User(username,password);
				userlist.add(newuser);
				break;
			}else{
				System.out.println("两次密码不一致，请重新输入！");
			}
		}
		System.out.println();
		System.out.println("****恭喜你~注册成功！****");
	 }
     
	private void login(){
		System.out.println();
		System.out.println("****欢迎登录！****");
		System.out.print("请输入用户名：");
		String login_username = scan.next();
		System.out.print("请输入密码：");
		String login_password = scan.next();
		for(int i=0;i<userlist.size();i++){
			String username = userlist.get(i).getUsername();
			String password = userlist.get(i).getUserpass();
			if(userlist.size() == 0){
				System.out.println();
				System.out.println("****呜呜呜~登录失败！****");
				System.out.println("*没有注册请选择1，不小心输入错误请选择2！*");
				break;
			}
			if(username.equals(login_username) && password.equals(login_password)){
				System.out.println();
				System.out.println("****锵锵锵~登录成功！****");
				break;
			}else{
				if(i == (userlist.size()-1)){
					System.out.println();
					System.out.println("****呜呜呜~登录失败！****");
					System.out.println("*没有注册请选择1，不小心输入错误请选择2！*");
					break;
				}	
			}
		}
		
	}
	
	private void adminlogin(){
		System.out.println();
		System.out.println("****欢迎管理员登录！****");
		while(true){
			System.out.print("请输入用户名：");
			String login_username = scan.next();
			System.out.print("请输入密码：");
			String login_password = scan.next();
			if(login_username.equals("admin") && login_password.equals("admin")){
				System.out.println();
				System.out.println("****锵锵锵~登录成功！****");
				break;
			}else{
				System.out.println();
				System.out.println("****呜呜呜~登录失败！****");
			}
			
		}
		boolean admingo = true;
		while(admingo){
			admingo = showAdminMenu();	
		}
		
		
	}
	
	private boolean showAdminMenu(){
		System.out.println();
		System.out.println("*****管理员专属*****");
    	System.out.println("\t1.添加商品");
    	System.out.println("\t2.修改商品信息");
    	System.out.println("\t3.删除商品");
    	System.out.println("\t4.查看所有商品");
    	System.out.println("\t5.查询单个商品");
    	System.out.println("\t6.退出");
    	System.out.println("************************");
    	System.out.print("请输入您的选择：");
    	int n = scan.nextInt();
    	return chooseAdmin(n);
	}
    
	private boolean chooseAdmin(int n){
		switch (n){
    	case 1:
    		addGood();
    		break;
    	case 2:
    		changeGood();
    		break;
    	case 3:
    		delGood();
    		break;
    	case 4:
    		showGood();
    		break;
    	case 5:
    		showOne();
    		break;
    	case 6:
    		System.out.println();
    		System.out.println("****你已退出管理员身份！****");
    		return false;
    	default:
    		System.out.println("选择有误，请重新输入！");
    		break;
    	}
		return true;
	}
	
	private void addGood(){
		boolean go = true;
		while(go){
			System.out.println();
			System.out.println("****添加商品****");
			System.out.print("请输入商品编号：");
			String id = scan.next();
			System.out.print("请输入商品名称：");
			String name = scan.next();
			System.out.print("请输入商品价格：");
			BigDecimal price = scan.nextBigDecimal();
			System.out.print("请输入商品库存量：");
			int num = scan.nextInt();
			Good newgood = new Good(id,name,price,num);
			goodlist.add(newgood);
			System.out.println();
			System.out.println("****存储成功！****");
			System.out.print("要继续存储商品吗？（Y/N）");
			String isgo = scan.next();
			if(isgo.equals("N") || isgo.equals("n")){
				go = false;
			}
		}
	}
	
	private void changeGood(){
		System.out.println();
		System.out.println("****修改商品****");
		boolean chggo = true;
		while(chggo){
			System.out.print("请输入要修改的商品编号：");
			String id = scan.next();
			for(int i=0;i<goodlist.size();i++){
				if(id.endsWith(goodlist.get(i).getId())){
					System.out.print("请输入商品名称修改后结果：");
					String name = scan.next();
					System.out.print("请输入商品价格修改后结果：");
					BigDecimal price = scan.nextBigDecimal();
					System.out.print("请输入商品库存量修改后结果：");
					int num = scan.nextInt();
					goodlist.get(i).setName(name);
					goodlist.get(i).setNum(num);
					goodlist.get(i).setPrice(price);
					System.out.println("****修改成功！****");
					chggo = false;
				}
			}
			if(chggo == false){
				break;
			}else{
				System.out.println("要修改的商品未找到，是否继续修改（Y/N）？");
				String chg = scan.next();
				if(chg.equals("N") || chg.equals("n")){
					chggo = false;
				}
			}
		}
	}
	
	private void delGood(){
		boolean delgo = true;
		while(delgo){
			System.out.print("请输入要删除的商品编号：");
			String id = scan.next();
			for(int i=0;i<goodlist.size();i++){
				if(id.endsWith(goodlist.get(i).getId())){
					goodlist.remove(i);
					System.out.println("****删除成功！****");
					delgo = false;
				}
			}
			if(delgo == false){
				break;
			}else{
				System.out.println("要删除的商品未找到，是否继续删除（Y/N）？");
				String del = scan.next();
				if(del.equals("N") || del.equals("n")){
					delgo = false;
				}
			}
		}
	}
	
	private void showGood(){
		System.out.println();
		System.out.println("****所有商品****");
		if(goodlist.size() == 0){
			System.out.println("现在商城还没有商品！");
		}else{
			for(int i=0;i<goodlist.size();i++){
				goodlist.get(i).showgood();
			}
		}
	}
	
	private void showOne(){
		System.out.println();
		System.out.println("****查看宝贝****");
		boolean showgo = true;
		while(showgo){
			System.out.print("请输入要查看的宝贝编号：");
			String id = scan.next();
			System.out.println("*****要查看的宝贝*****");
			for(int i=0;i<goodlist.size();i++){
				if(id.equals(goodlist.get(i).getId())){
					goodlist.get(i).showgood();
					showgo = false;
				}
			}
			if(showgo == false){
				break;
			}else{
				System.out.println("要查看的商品未找到，是否继续查看（Y/N）？");
				String fin = scan.next();
				if(fin.equals("N") || fin.equals("n")){
					showgo = false;
				}
			}
		}
	}
}
