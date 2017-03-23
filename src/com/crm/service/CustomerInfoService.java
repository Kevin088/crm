/**
 * 
 */
package com.crm.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.crm.dao.CustomerInfoMapper;
import com.crm.dao.CustomerMapper;
import com.crm.model.Contact;
import com.crm.model.Customer;
import com.crm.model.CustomerInfo;
import com.crm.model.Visitlog;
import com.crm.model.easyui.PageHelper;
import com.crm.pojo.CustomerInfoPojo;
import com.crm.pojo.CustomerInfoSearchPojo;
import com.crm.util.CustomerInfoUtils;

/**
 * @author zh
 */
/** 
 * Cacheable注解        负责将方法的返回值加入到缓存中 
 * CacheEvict注解     负责清除缓存(它的三个参数与@Cacheable的意思是一样的) 
 * ---------------------------------------------------------------------------------------------------------- 
 * value------缓存位置的名称,不能为空,若使用EHCache则其值为ehcache.xml中的<cache name="myCache"/> 
 * key--------缓存的Key,默认为空(表示使用方法的参数类型及参数值作为key),支持SpEL 
 * condition--只有满足条件的情况才会加入缓存,默认为空(表示全部都加入缓存),支持SpEL 
 * 该注解的源码位于spring-context-*.RELEASE-sources.jar中 
 * Spring针对Ehcache支持的Java源码位于spring-context-support-*.RELEASE-sources.jar中 
 * ---------------------------------------------------------------------------------------------------------- 
 */
@Service
public class CustomerInfoService {

	@Resource
	private CustomerInfoMapper customerInfoMapper;

	public Customer findByName(String name) {
		return customerInfoMapper.findByName(name);
	}
	
    public Customer getUsernameById(int id){  
        return customerInfoMapper.getCustomerById(id);  
    }

	/**
	 * 获取该客户的所有联系人
	 * @return
	 */
	public List<Contact> getContactById(int customerId) {
		return customerInfoMapper.getContactById(customerId);  
	}
	


	/**
	 * 获取总数
	 * @param user
	 * @return
	 */
	public Long getDatagridTotal(CustomerInfo customerInfo) {
		return customerInfoMapper.getDatagridTotal(customerInfo);
	}

	/**
	 * 获列表 分页
	 * @param page
	 * @return
	 */
	public List<CustomerInfoPojo> datagridCustomer(PageHelper page,CustomerInfoPojo customerInfoPojo) {
		page.setStart((page.getPage()-1)*page.getRows());
		page.setEnd(page.getPage()*page.getRows());
		CustomerInfoSearchPojo pageInfoPojo=new CustomerInfoSearchPojo();
		
		pageInfoPojo.setName(customerInfoPojo.getName());
		pageInfoPojo.setUserName(customerInfoPojo.getUserName());
		pageInfoPojo.setSort(page.getSort());
		pageInfoPojo.setOrder(page.getOrder());
		pageInfoPojo.setStart(page.getStart());
		pageInfoPojo.setEnd(page.getEnd());
		return customerInfoMapper.datagridCustomerInfo(pageInfoPojo);  
	}

	//添加
	public void addCustomerInfo(CustomerInfo customerInfo){
		customerInfo.setDatetime(new Timestamp(new Date().getTime()));
		customerInfoMapper.addCustomerInfo(customerInfo);
	};

	//修改
	public void editCustomerInfo(CustomerInfo customerInfo){
		customerInfoMapper.editCustomerInfo(customerInfo);
	};
	
	//删除
	public void deleteCustomerInfo(int id){
		customerInfoMapper.deleteCustomerInfo(id);
	};
	//导出
	public void exportCustomerInfo(HttpServletResponse res) throws Exception{
		String[] titles=new String[]{"客户姓名","联系方式","地址","是否有电脑","宽带运营商","宽带满意程度"
				,"是否融合","宽带资费","宽带到期","电视运营商","电视满意程度","电视资费","电视到期","县分"
				,"创建人","创建时间"};
		List<CustomerInfoPojo> list=customerInfoMapper.datagridCustomerInfo(new CustomerInfoSearchPojo());  

		/**
	       * 以下为生成Excel操作
	       */
	      // 1.创建一个workbook，对应一个Excel文件
	      HSSFWorkbook wb = new HSSFWorkbook();
	      // 2.在workbook中添加一个sheet，对应Excel中的一个sheet
	      HSSFSheet sheet = wb.createSheet("XXX表");
	      // 3.在sheet中添加表头第0行，老版本poi对excel行数列数有限制short
	      HSSFRow row = sheet.createRow((int) 0);
	      // 4.创建单元格，设置值表头，设置表头居中
	      HSSFCellStyle style = wb.createCellStyle();
	      // 居中格式
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	 
	      // 设置表头
	      HSSFCell cell = null;
	      for(int i=0;i<titles.length;i++){
	    	  cell=row.createCell(i);
		      cell.setCellValue(titles[i]);
		      cell.setCellStyle(style); 
	      }
	   // 循环将数据写入Excel
	      
	      for (int i = 0; i < list.size(); i++) {
	        row = sheet.createRow((int) i + 1);
	        CustomerInfoPojo bean= list.get(i);
	        // 创建单元格，设置值
	        row.createCell(0).setCellValue(bean.getName()+"");
	        row.createCell(1).setCellValue(bean.getTelephone()+"");
	        row.createCell(2).setCellValue(bean.getAddress()+"");
	        row.createCell(3).setCellValue(CustomerInfoUtils.formatIsOrNo(bean.getIscompute())+"");
	        row.createCell(4).setCellValue(CustomerInfoUtils.formatBroadband(bean.getBroadband())+"");
	        row.createCell(5).setCellValue(CustomerInfoUtils.formatBroadbandSatisfy(bean.getBroadbandSatisfy())+"");
	        row.createCell(6).setCellValue(CustomerInfoUtils.formatIsOrNo(bean.getIsBroadbandFusion())+"");
	        row.createCell(7).setCellValue(bean.getBroadbandPrice()+"");
	        row.createCell(8).setCellValue(bean.getBroadbandEndTime()+"");
	        row.createCell(9).setCellValue(CustomerInfoUtils.formatTv(bean.getTv())+"");
	        row.createCell(10).setCellValue(CustomerInfoUtils.formatBroadbandSatisfy(bean.getTvSatisfy())+"");
	        row.createCell(11).setCellValue(bean.getTvPrice()+"");
	        row.createCell(12).setCellValue(bean.getTvEndTime()+"");
	        row.createCell(13).setCellValue(bean.getDistrictName()+"");
	        row.createCell(14).setCellValue(bean.getUserName()+"");
	        row.createCell(15).setCellValue(bean.getDatetime()+"");
	      }
	      
	      
	      String fileName = "客户信息表";
	      ByteArrayOutputStream os = new ByteArrayOutputStream();
	      wb.write(os);
	      byte[] content = os.toByteArray();
	      InputStream is = new ByteArrayInputStream(content);
	      // 设置response参数，可以打开下载页面
	      res.reset();
	      res.setContentType("application/vnd.ms-excel;charset=utf-8");
	      res.setHeader("Content-Disposition", "attachment;filename="
	          + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
	      ServletOutputStream out = res.getOutputStream();
	      BufferedInputStream bis = null;
	      BufferedOutputStream bos = null;
	 
	      try {
	        bis = new BufferedInputStream(is);
	        bos = new BufferedOutputStream(out);
	        byte[] buff = new byte[2048];
	        int bytesRead;
	        // Simple read/write loop.
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	          bos.write(buff, 0, bytesRead);
	        }
	      } catch (Exception e) {
	        // TODO: handle exception
	        e.printStackTrace();
	      } finally {
	        if (bis != null)
	          bis.close();
	        if (bos != null)
	          bos.close();
	      }
		
	};
	
    
}
