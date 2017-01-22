package id.web.twoh.retrofitsample.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.net.URL;
import java.util.List;

@Root(name="map")
public class Map {

    @Element(name="message", required=false)
    String message;

    @Element(name="status", required=false)
    String status;

    public String getMessage() {return this.message;}
    public void setMessage(String value) {this.message = value;}

    public String getStatus() {return this.status;}
    public void setStatus(String value) {this.status = value;}

}