package galitt.kn.service;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.*;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galitt.kn.service.hce.Transaction;

/**
 * Created by Adrien on 22/09/2015.
 */
public class ObjectFromResponseFactory {
    static private Map GetMapFromText(String jsonText) throws ParseException{
        JSONParser parser = new JSONParser();

        ContainerFactory containerFactory = new ContainerFactory(){
            public List creatArrayContainer() {
                return new LinkedList();
            }

            public Map createObjectContainer() {
                return new LinkedHashMap();
            }
        };

        Map json = (Map)parser.parse(jsonText, containerFactory);
        return json;
    }

    static public Transaction GetTransaction(String jsonText) throws ParseException{
        Transaction t;

        Map jsonMap = GetMapFromText(jsonText);
        Iterator iter = jsonMap.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            if(entry.getKey().toString().equals("apdu")){
                t = new Transaction((entry.getValue().toString()));

                return t;
            }
        }

        return null;
    }
}
