
package ir.home.model;

import ir.home.utility.Json;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;

public class TbMessage {
    private int id;
    private int categoryId;
    private String categoryTitle;
    private int userId;
    private String userName;
    private String description;
    private Date registerDate;
    private String sendDate;
    private int share;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
    
    
    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }


    public static TbMessage ToEntity(SoapObject obj) {
        TbMessage result = null;
        if (obj != null) {
            result = new TbMessage();
            result.setId(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("Id")));
            result.setCategoryId(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("CategoryId")));
            result.setUserId(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("UserId")));
            result.setDescription(obj
                    .getPrimitivePropertySafelyAsString("Description"));
            result.setCategoryTitle(obj
                    .getPrimitivePropertySafelyAsString("CategoryTitle"));
            result.setUserName(obj
                    .getPrimitivePropertySafelyAsString("UserName"));
            String regdate = obj
                    .getPrimitivePropertySafelyAsString("RegisterDate");
            result.setSendDate(obj
                    .getPrimitivePropertySafelyAsString("SendDate"));
            result.setShare(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("Share")));
            try {
                Date regDate = DateFormat.getInstance().parse(regdate);
                result.setRegisterDate(regDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public static List<TbMessage> ToList(SoapObject obj) {
        List<TbMessage> result = null;
        if (obj != null) {
            result = new ArrayList<TbMessage>();
            int count = obj.getPropertyCount();
            for (int i = 0; i < count; i++) {                           
                result.add(ToEntity((SoapObject) obj.getProperty(i)));
            }
        }

        return result;
    }
    
    
    public static List<TbMessage> ToList(JSONArray datas) throws JSONException {

        List<TbMessage> messages = new ArrayList<TbMessage>();

        for (int i = 0; i < datas.length(); i++) {
            JSONObject obj = datas.getJSONObject(i);
            messages.add(ToEntity(obj));
        }
        return messages;
    }

    public static TbMessage ToEntity(JSONObject obj) throws JSONException {
        TbMessage msg = new TbMessage();
        
        msg.setCategoryId(obj.getInt("CategoryId"));
        msg.setCategoryTitle(obj.getString("CategoryTitle"));
        msg.setDescription(obj.getString("Description"));
        msg.setId(obj.getInt("Id"));

        msg.setRegisterDate(Json.JsonDateToDate(obj.getString("RegisterDate")));

        msg.setSendDate(Json.JsonDateToDate(obj.getString("SendDate")).toString());
        msg.setShare(obj.getInt("Share"));
        msg.setUserId(obj.getInt("UserId"));

        return msg;
    }
}
