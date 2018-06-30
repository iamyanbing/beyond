package com.beyond.util.xml;

import com.beyond.util.xml.vo.Student;
import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 16:14
 */
public class JAXBUtils {

    public static String getStringByObject(Student student) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(student.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        // 不符的处理进行转义字
        marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
            @Override
            public void escape(char[] ch, int start, int length, boolean isAttVal, Writer out) throws IOException {
                out.write(ch, start, length);
            }
        });
        // 去掉生成xml的默认报文头
        //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        // xml格式化,值若为true，则生成的xml会带有换行，且有缩进
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(student, writer);
        return writer.toString();
    }

    public static Student getObjectByXML(String xml) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Student) unmarshaller.unmarshal(new StringReader(xml));
    }
}
