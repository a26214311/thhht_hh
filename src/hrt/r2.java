package hrt;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;
public class r2 {

	public static void main(String[] args) {
		System.out.println("start:");
		try {
//			byte[] e = new byte[2];
//			e[0]=(byte)0xA1;
//			e[1]=(byte)0xA1;
//			String t = new String(e);
//			System.out.println(t);
//			pre();
			write();
//			readhht(4195);
//			readhht(4196);
			
//			repo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("finish");
	}
	
	private static Map<String, ArrayList<String>> sss = new HashMap<String, ArrayList<String>>();
	public static void repo() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("fw/4195sk.txt")));
		String s;
		String r= new String();
		while ((s = br.readLine()) != null) {
			if(s.indexOf("||||")<0&&s.length()>5){
				String[] sa = s.trim().split(",");
				if(sa.length!=2){
					System.out.println("err: no 2"+s);
				}else{
					int st = Integer.valueOf(sa[0]);
					int len = Integer.valueOf(sa[1]);
					byte[] tm = new byte[len];
					String txt = br.readLine();
					int n1 = txt.indexOf("||||");
					String hh = txt.substring(0,n1).trim();
					String oh = txt.substring(n1+5).trim();
					ArrayList<String> ssaa = sss.get(oh);
					if(ssaa==null){
						ssaa = new ArrayList<String>();
						ssaa.add(s);
						sss.put(oh, ssaa);
					}else{
						ssaa.add(s);
						sss.put(oh, ssaa);
					}
					
				}
			}
		}
		br.close();
		
		
		br = new BufferedReader(new InputStreamReader(new FileInputStream("fw/4195o.txt")));
		while ((s = br.readLine()) != null) {
			if(s.indexOf("||||")<0&&s.length()>5){
				String[] sa = s.trim().split(",");
				if(sa.length!=2){
					System.out.println("err: no 2"+s);
				}else{
					int st = Integer.valueOf(sa[0]);
					int len = Integer.valueOf(sa[1]);
					byte[] tm = new byte[len];
					String txt = br.readLine();
					int n1 = txt.indexOf("||||");
					String hh = txt.substring(0,n1).trim();
					String oh = txt.substring(n1+5).trim();
					ArrayList<String> oo = sss.get(oh);
					if(oo==null){
						System.out.println(oh);
					}else{
						for(int i=0;i<oo.size();i++){
							String nl = sss.get(oh).get(i);
							r=r+nl+"\r\n";
							r=r+txt+"\r\n";
							r=r+"\r\n";
						}
						sss.remove(oh);
					}


				}
			}
		}
		br.close();
		System.out.println(sss);
		
		FileWriter fw= new FileWriter("fw/4195n.txt");
		fw.write(r);
		fw.close();
			
	}
	
	
	public static void pre() throws Exception{
		String r = new String();
		r=r+prewrite("fw/4044-"+1+".txt");
		r=r+prewrite("fw/4044-"+2+".txt");
		r=r+prewrite("fw/4044-"+3+".txt");
		r=r+prewrite("fw/4044-"+4+".txt");
		FileWriter fw = new FileWriter("fw/4195o.txt");
		fw.write(r);
		fw.close();
	}
	
	public static String  prewrite(String fn) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fn)));
		String s;
		String r= new String();
		while ((s = br.readLine()) != null) {
			r = r + s + "\r\n";
		}
		br.close();
		return r+ "\r\n";
	}
	
	
	public static void write() throws Exception{
		byte[] x = toByteArray2("fw/MOGAI18.exe");
		byte[] x1 = writehht(x, 4195);
		byte[] x2 = writehht(x1, 4196);
		FileOutputStream fo = new FileOutputStream("fw/MOGAI18-c.exe");
		fo.write(x2);
		fo.close();
		
		FileOutputStream fo2 = new FileOutputStream("fw/thhht/e.exe");
		fo2.write(x2);
		fo2.close();
	}
	
	
	
	public static byte[] writehht(byte[] x,int line)throws Exception{
		int c=0;
		int r=0;
		int ts=0;
		int te=0;
		for(int i=0;i<x.length;i++){
			if(x[i]==10){
				c++;
				if(c==line-1){
					ts=i+1;
				}
				if(c==line){
					System.out.println(r);
					te=i-1;
				}
				r=0;
			}
			r++;
		}
		System.out.println(ts);
		System.out.println(te);
		int len = te-ts;
		byte[] mu = new byte[len];
		System.arraycopy(x, ts, mu, 0, len);
		
		
		byte[] nm = replacemubyte(line,mu);
		
		System.arraycopy(nm, 0, x, ts, nm.length);
		return x;
//		FileOutputStream fo = new FileOutputStream("fw/e");
//		fo.write(x);
//		fo.close();
	}
	
	private static ArrayList<String> forbidchar = new ArrayList<String>();
	static{
		forbidchar.add("£");
		forbidchar.add("〜");
		forbidchar.add("　");
	}
	public static byte[] replacemubyte(int line,byte[] mu) throws Exception{
		byte[] r = new byte[mu.length];
		System.arraycopy(mu, 0, r, 0, mu.length);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("fw/"+line+"n.txt")));
		String s;
		while ((s = br.readLine()) != null) {
			if(s.indexOf("||||")<0&&s.length()>5){
				String[] sa = s.trim().split(",");
				if(sa.length!=2){
					System.out.println("err: no 2"+s);
				}else{
					int st = Integer.valueOf(sa[0]);
					int len = Integer.valueOf(sa[1]);
					byte[] tm = new byte[len];
					String txt = br.readLine();
					int n1 = txt.indexOf("||||");
					String hh = txt.substring(0,n1).trim();
					String oh = txt.substring(n1+5).trim();

					
					boolean dir=true;
					if(st>=1679&&st<=1795){
						dir=false;
					}
					if(st==5289||st==7397||st==21663||st==17835){
						dir=false;
					}
					
					if(hh.equals(oh)&&dir){
//						System.out.println("same:"+hh+","+st);
					}else{
						
						for(int i=0;i<forbidchar.size();i++){
							String fc = forbidchar.get(i);
							int nnn = hh.indexOf(fc);
							if(nnn>-1){
//								System.out.println("has forbid char:"+hh);
							}
						}
						if(line==4196){
							hh = hh.replaceAll("　", " 0");
						}else{
							hh = hh.replaceAll("　", " ");
						}
						byte[] bh = hh.getBytes("gbk");
						if(bh.length>tm.length){
							System.arraycopy(bh, 1, tm, 0, len-1);
							tm[0]=(byte)65;
							System.arraycopy(tm, 0, r, st, len);
							System.out.println("exceed:"+hh+","+oh+","+bh.length+","+tm.length);
						}else{
							System.arraycopy(bh, 0, tm, 0, bh.length);
							System.arraycopy(tm, 0, r, st, len);
						}
					}
							
				}
			}
		}
		br.close();
		return r;
	}
	
	
	public static void readhht(int line) throws Exception{
		byte[] x = toByteArray2("fw/o");
		int c=0;
		int r=0;
		int ts=0;
		int te=0;
		for(int i=0;i<x.length;i++){
			if(x[i]==10){
				c++;
				if(c==line-1){
					ts=i+1;
				}
				if(c==line){
					System.out.println(r);
					te=i-1;
				}
				r=0;
			}
			r++;
		}
		System.out.println(ts);
		System.out.println(te);
		int len = te-ts;
		System.out.println(len);
		byte[] mu = new byte[len];
		System.arraycopy(x, ts, mu, 0, len);
		byte[] pt = new byte[671];
		System.arraycopy(mu, 0, pt, 0, 671);
		pthex(pt);
		int lz=-1;
		ArrayList<Section> stt= new ArrayList<Section>();
		for(int i=0;i<mu.length;i++){
			if(mu[i]==0){
				if(i-lz==1){
					lz=i;
				}else{
					int ss=lz+1;
					int slen = i-lz-1;
					byte[] st = new byte[slen];
					System.arraycopy(mu, lz+1, st, 0, slen);
					lz=i;
					Section tss = new Section(st);
					tss.start=ss;
					tss.len=slen;
					stt.add(tss);
				}
			}else{
				
			}
		}
		String ret = new String();
		for(int i=0;i<stt.size();i++){
			byte[] tt = stt.get(i).data;
			String ss = new String(tt,"shift-JIS");
			String trr = stt.get(i).start+","+stt.get(i).len+"\r\n"+ss + "				||||				"+ss+"\r\n";
			ret = ret + trr + "\r\n";
		}
		FileWriter fw = new FileWriter("fw/"+line+"sk.txt");
		fw.write(ret);
		fw.close();
	}
	
	
	public static byte[] getnewbyte(byte[] x){
		ArrayList<Byte> xi = new ArrayList<Byte>();
		for(int i=0;i<x.length;i++){
			if(x[i]!=(byte)0xC2&&x[i]!=(byte)0xC3){
				xi.add(x[i]);
			}
		}
		byte[] r = new byte[xi.size()];
		for(int i=0;i<r.length;i++){
			r[i]=xi.get(i);
		}
		return r;
		
	}
	
	public static void pt(byte[] x){
		for(int i=0;i<x.length;i++){
			if(i==0){
				System.out.print(x[i]);
			}else{
				System.out.print(","+x[i]);
			}
		}
		System.out.println();
	}
	

	
	public static void pthex(byte[] x){
		String sr = DatatypeConverter.printHexBinary(x);
		
		for(int i=0;i<sr.length();i=i+2){
			System.out.print(sr.substring(i, i+2)+",");
		}
		System.out.println();
	}
	

	 public static String readOriginal2Hex(String filename) throws IOException {
		        FileInputStream fin = new FileInputStream(new File(filename));
		        StringWriter sw = new StringWriter();

		        int len = 1;
		        byte[] temp = new byte[len];

		       /*16进制转化模块*/
		        for (; (fin.read(temp, 0, len)) != -1;) {
		            if (temp[0] > 0xf && temp[0] <= 0xff) { 
		                sw.write(Integer.toHexString(temp[0]));
		            } else if (temp[0] >= 0x0 && temp[0] <= 0xf) {//对于只有1位的16进制数前边补“0”
		                sw.write("0" + Integer.toHexString(temp[0]));
		            } else { //对于int<0的位转化为16进制的特殊处理，因为Java没有Unsigned int，所以这个int可能为负数
		                sw.write(Integer.toHexString(temp[0]).substring(6));
		            }
		        }

		        return sw.toString();
		    }
	
	public static byte[] toByteArray2(String filename) throws IOException {  
		  
	        File f = new File(filename);  
	        if (!f.exists()) {  
	            throw new FileNotFoundException(filename);  
	        }  
	  
	        FileChannel channel = null;  
	        FileInputStream fs = null;  
	        try {  
	            fs = new FileInputStream(f);  
//	            byte[] bb = new byte[100];
//	            fs.read(bb);
//	            pthex(bb);
	            channel = fs.getChannel();
	            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());  
	            while ((channel.read(byteBuffer)) > 0) {  
	                // do nothing  
	                // System.out.println("reading");  
	            }  
	            return byteBuffer.array();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            throw e;  
	        } finally {  
	            try {  
	                channel.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	            try {  
	                fs.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
}
