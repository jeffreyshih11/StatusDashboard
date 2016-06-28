import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLStreamWriter;

public class CreateXML {
    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Environment.class);
        OutputStream os = new FileOutputStream("example.xml");

        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Environment baseline = new Environment("Baseline", "20673", "Bob", "6/28/2016 8:00 AM", "Up");
        Environment demo = new Environment("Demo", "20658", "Bill", "6/28/2016 8:00 AM", "Up");
        Environment dev = new Environment("Dev", "20644", "Barry", "6/28/2016 8:00 AM", "Building");
        Environment gat = new Environment("Gat", "20658", "Brenda", "6/28/2016 8:00 AM", "Up");
        Environment qa = new Environment("Qa", "20673", "Brandy", "6/28/2016 8:00 AM", "Down");
        Environment uat = new Environment("Uat", "20673", "Barbara", "6/28/2016 8:00 AM", "Up");

        m.marshal(baseline, os);
        m.marshal(demo, os);
        m.marshal(dev, os);
        m.marshal(gat, os);
        m.marshal(qa, os);
        m.marshal(uat, os);

    }
}

@XmlRootElement
class Environment {
    private String Name;

    private String Revision;

    private String Builder;

    private String Date;

    private String Status;

    public Environment()
    {
        Name = "";
        Revision = "";
        Builder = "";
        Date = "";
        Status = "";
    }

    public Environment (String name, String revision, String builder, String date, String status){
        Name = name;
        Builder = builder;
        Revision = revision;
        Date = date;
        Status = status;
    }

    @XmlAttribute
    public String getName() {
        return Name;
    }

    public void setName(String code) {
        this.Name = code;
    }

    public String getRevision() {
        return Revision;
    }

    public void setRevision(String name) {
        this.Revision = name;
    }

    public String getBuilder() {
        return Builder;
    }

    public void setBuilder(String population) {
        this.Builder = population;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }
}
