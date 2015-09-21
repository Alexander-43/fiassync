package ru.test.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropWriter {
	public static final Logger logger = Logger.getLogger(PropWriter.class);
	String propFile;
	String prop;
	Properties props = new Properties();
	
	public void init() throws IOException{
		this.props.clear();
		this.props.load(getClass().getResourceAsStream(propFile));
	}
	
	public Properties getProps(){
		return this.props;
	}
	
	public void update(Map<String,String> additional) throws IOException{
		for(String key : additional.keySet()){
			props.setProperty(key, additional.get(key));
		}
		File f = new File(propFile);
        OutputStream out = new FileOutputStream( f );
        props.store(out, "This is an optional header comment string");
	}
	
	public String getPropFile() {
		logger.error("Set propperties file - " + propFile);
		return propFile;
	}

	public void setPropFile(String propFile) {
		logger.error("Set propperties file - " + propFile);
		this.propFile = propFile;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		logger.error("Set propperties file - " + prop);
		this.prop = prop;
	}
}
