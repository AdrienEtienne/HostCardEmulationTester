package galitt.kn.service.hce;

import java.util.*;
import galitt.kn.service.IJsonObject;
import org.json.simple.JSONObject;
/**
 * Created by Adrien on 22/09/2015.
 */
public class Transaction implements IJsonObject {
    private String _apdu;

    public Transaction(String apdu){
        _apdu = apdu;
    }

    @Override
    public String ObjectToJson() {
        JSONObject obj=new JSONObject();
        obj.put("apdu",_apdu);
        return obj.toJSONString();
    }

    public String GetApdu(){
        return _apdu;
    }
}
