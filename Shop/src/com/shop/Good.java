	package com.shop;
	import java.io.Serializable;
	import java.math.BigDecimal;


    public class Good implements Serializable{
	   
		private String id;     // 编号
		private String name;   // 姓名
		private float price;   // 价格
		private int num;        // 库存量   购买数量
		
		Good(){
			
		}
		
		Good(String id,String name,float price,int num){
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
		
		public void setPrice(float price){
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
		
		public float getPrice(){
			return price;
		}
		
		public int getNum(){
			return num;
		}
		
		public void showgood(){
			System.out.println("编号:"+id+"   宝贝:"+name+"   价格:"+price+"   数量:"+num);
		}

}
