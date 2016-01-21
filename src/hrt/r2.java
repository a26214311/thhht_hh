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

import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;
public class r2 {

	public static void main(String[] args) {
		System.out.println("start:");
		String path="Talks/";
		try {
			
			
			writehht();
			

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("finish");
	}
	
	public static void writehht()throws Exception{
		byte[] x = toByteArray2("fw/9");
		int c=0;
		int r=0;
		int ts=0;
		int te=0;
		for(int i=0;i<x.length;i++){
			if(x[i]==10){
				c++;
				if(c==4043){
					ts=i+1;
				}
				if(c==4044){
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
		
		
		byte[] nm = replacemubyte(mu);
		
		System.arraycopy(nm, 0, x, ts, nm.length);
		
		
		FileOutputStream fo = new FileOutputStream("fw/e");
		fo.write(x);
		fo.close();
	}
	
	public static byte[] replacemubyte(byte[] mu) throws Exception{
		byte[] r = new byte[mu.length];
		System.arraycopy(mu, 0, r, 0, mu.length);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("fw/4044.txt")));
		String s;
		String tex;
		while ((s = br.readLine()) != null) {
			if(s.indexOf("||||")<0&&s.length()>5){
				String[] sa = s.trim().split(",");
				if(sa.length!=2){
					System.out.println("err: no 2");
				}else{
					int st = Integer.valueOf(sa[0]);
					int len = Integer.valueOf(sa[1]);
					byte[] tm = new byte[len];
					String txt = br.readLine();
					int n1 = txt.indexOf("||||");
					String hh = txt.substring(0,n1).trim();
					String oh = txt.substring(n1+5).trim();
					if(hh.equals(oh)){
						
					}else{
						byte[] bh = hh.getBytes();
						System.out.println(bh.length+","+tm.length);
						if(bh.length>tm.length){
							System.out.println("exceed:"+hh+","+oh);
						}else{
							
							System.arraycopy(bh, 0, tm, 0, bh.length);
							pthex(tm);
							System.arraycopy(tm, 0, r, st, len);
						}
					}
							
				}
			}
		}
		
		return r;
	}
	
	
	public static void readhht() throws Exception{
		byte[] x = toByteArray2("fw/9");
		int c=0;
		int r=0;
		int ts=0;
		int te=0;
		for(int i=0;i<x.length;i++){
			if(x[i]==10){
				c++;
				if(c==4043){
					ts=i+1;
				}
				if(c==4044){
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
		FileWriter fw = new FileWriter("fw/4044.txt");
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
