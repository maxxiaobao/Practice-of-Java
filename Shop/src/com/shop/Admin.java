package com.shop;

import java.io.Serializable;
import java.math.BigDecimal;

public class Admin extends User implements Serializable{
	
	//管理员登录
	public void AdminLogin(){
		System.out.println();
		System.out.println("****欢迎管理员登录！****");
		while(true){
			System.out.print("请输入用户名：");
			String login_username = Shop.scan.next();
			System.out.print("请输入密码：");
			String login_password = Shop.scan.next();
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
	
	//管理员专属
	public boolean showAdminMenu(){
		System.out.println();
		System.out.println("*****管理员专属*****");
    	System.out.println("\t1.添加商品");
    	System.out.println("\t2.修改商品信息");
    	System.out.println("\t3.删除商品");
    	System.out.println("\t4.查看所有商品");
    	System.out.println("\t5.查询单个商品");
    	System.out.println("\t6.管理用户");
    	System.out.println("\t7.退出");
    	System.out.println("************************");
    	System.out.print("请输入您的选择：");
    	int n = Shop.scan.nextInt();
    	return chooseAdmin(n);
	}
	
	//管理员选择
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
    		mangeUser();
    		break;
    	case 7:
    		System.out.println();
    		System.out.println("****你已退出管理员身份！****");
    		return false;
    	default:
    		System.out.println("选择有误，请重新输入！");
    		break;
    	}
		return true;
	}
	
	//管理用户
	public void mangeUser(){
		showUsers();
		System.out.println("请输入要删除的用户名：");
		String del = Shop.scan.next();
		delUser(del);
		System.out.println("******删除成功！*****");
	}
	
	//显示用户
	public void showUsers(){
		for(int i=0;i<Shop.userList.size();i++){
			if(Shop.userList.size() == 0){
				System.out.println("系统暂时没有用户！");
				break;
			}
			Shop.userList.get(i).showUser();
		}
	}
	
	//删除用户
	public void delUser(String name){
		Shop.userList.remove(FindUser(name));
		Shop.userGood.remove(FindUser(name));
	}
	
	//添加商品
	public void addGood(){
		boolean go = true;
		while(go){
			System.out.println();
			System.out.println("****添加商品****");
			System.out.print("请输入商品编号：");
			String id = Shop.scan.next();
			System.out.print("请输入商品名称：");
			String name = Shop.scan.next();
			System.out.print("请输入商品价格：");
			float price = Shop.scan.nextFloat();
			System.out.print("请输入商品库存量：");
			int num = Shop.scan.nextInt();
			Good newgood = new Good(id,name,price,num);
			Shop.goodList.add(newgood);
			System.out.println();
			System.out.println("****存储成功！****");
			System.out.print("要继续存储商品吗？（Y/N）");
			String isgo = Shop.scan.next();
			if(isgo.equals("N") || isgo.equals("n")){
				go = false;
			}
		}
	}
	
	//修改商品
	public void changeGood(){
		System.out.println();
		System.out.println("****修改商品****");
		boolean chggo = true;
		while(chggo){
			System.out.print("请输入要修改的商品编号：");
			String id = Shop.scan.next();
				if(FindGood(id)!=null){
					Good nowgood = FindGood(id);
					System.out.print("请输入商品名称修改后结果：");
					String name = Shop.scan.next();
					System.out.print("请输入商品价格修改后结果：");
					float price = Shop.scan.nextFloat();
					System.out.print("请输入商品库存量修改后结果：");
					int num = Shop.scan.nextInt();
					nowgood.setName(name);
					nowgood.setNum(num);
					nowgood.setPrice(price);
					System.out.println("****修改成功！****");
					chggo = false;
				}else{
					System.out.println("要修改的商品未找到，是否继续修改（Y/N）？");
					String chg = Shop.scan.next();
					if(chg.equals("N") || chg.equals("n")){
						chggo = false;
					}
				}
			
		}
	}
    
	//删除商品
	public void delGood(){
		boolean delgo = true;
		while(delgo){
			System.out.print("请输入要删除的商品编号：");
			String id = Shop.scan.next();
			Good nowgood = FindGood(id);	
			if(nowgood!=null ){
					Shop.goodList.remove(nowgood);
					System.out.println("****删除成功！****");
					delgo = false;
			}else{
				System.out.println("要删除的商品未找到，是否继续删除（Y/N）？");
				String del = Shop.scan.next();
				if(del.equals("N") || del.equals("n")){
					delgo = false;
				}
			}
		 }
	}
	
	//查找商品
	private void showOne(){
		System.out.println();
		System.out.println("****查看宝贝****");
		boolean showgo = true;
		while(showgo){
			System.out.print("请输入要查看的宝贝编号：");
			String id = Shop.scan.next();
			System.out.println("*****要查看的宝贝*****");
			Good nowgood = FindGood(id);
				if(nowgood != null){
					nowgood.showgood();
					showgo = false;
				}else{
				System.out.println("要查看的商品未找到，是否继续查看（Y/N）？");
				String fin = Shop.scan.next();
				if(fin.equals("N") || fin.equals("n")){
					showgo = false;
				}
			}
		}
	}
}
