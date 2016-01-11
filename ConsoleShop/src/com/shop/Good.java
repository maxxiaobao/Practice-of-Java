package com.shop;

import java.math.BigDecimal;

public class Good {
	private String id;     // 编号
	private String name;   // 姓名
	private BigDecimal price;   // 价格
	private int num;        // 库存量
	
	Good(String id,String name,BigDecimal price,int num){
		this.id = id;
		this.name = name;
		this.price = price;
		this.num = num;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	
	public void setNum(int num){
		this.num = num;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getName(){
		return name;
	}
	
	public BigDecimal getPrice(){
		return price;
	}
	
	public int getNum(){
		return num;
	}
	
	public void showgood(){
		System.out.println("编号:"+id+"   宝贝:"+name+"   价格:"+price+"   库存量:"+num);
	}
}
