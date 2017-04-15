package com.yoveefisher.jaxb.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.yoveefisher.entity.Student;

public class StudentJaxbTest {
	
	public static void main(String[] args) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
			
			Marshaller marshaller = jaxbContext.createMarshaller();
			Student student = new Student();
			
			student.setSex(Boolean.TRUE);
			student.setAge(30);
			student.setName("大鱼");
			
			marshaller.marshal(student, System.out);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

}
