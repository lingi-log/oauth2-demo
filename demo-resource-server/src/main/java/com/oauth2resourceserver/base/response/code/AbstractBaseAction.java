package com.oauth2resourceserver.base.response.code;// package com.kohyoung.api.core.base;

// import com.fasterxml.jackson.databind.DeserializationConfig;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.DeserializationFeature;
// import com.fasterxml.jackson.databind.SerializationFeature;
// import com.kohyoung.api.core.dto.ResponseBodyDto;
// import com.kohyoung.api.core.exception.BusinessLogicException;
// import com.kohyoung.common.Constants;
// import com.kohyoung.common.util.SessionUtil;
// import com.opensymphony.xwork2.ActionSupport;
// import com.opensymphony.xwork2.config.entities.Parameterizable;
// import lombok.Getter;
// import lombok.Setter;
// import org.apache.commons.collections.MapUtils;
// import org.apache.commons.collections4.CollectionUtils;
// import org.apache.commons.lang3.ArrayUtils;
// import org.apache.commons.lang3.reflect.FieldUtils;
// import org.apache.log4j.Logger;
// import org.apache.struts2.ServletActionContext;
// import org.spockframework.util.ReflectionUtil;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
// import java.io.IOException;
// import java.io.OutputStream;
// import java.lang.reflect.Field;
// import java.text.SimpleDateFormat;
// import java.util.Collection;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.TimeZone;
// //import org.codehaus.jackson.map.DeserializationConfig;
// //import org.codehaus.jackson.map.ObjectMapper;

// /**
//  * AbstractBaseAction
//  *
//  * @author Daniel Nguyen
//  * @since 2.0.0
//  */
// public abstract class AbstractBaseAction extends ActionSupport implements Parameterizable {



//     /*================================================================================================================
//      *===== CONSTANT VALUES                                                                                      =====
//      *================================================================================================================*/

//     /*================================================================================================================
//      *===== PRIVATE PROPERTIES                                                                                   =====
//      *================================================================================================================*/

//     /**
//      * Object Mapper
//      */
//     private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

//     static {
//         OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//         OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//         OBJECT_MAPPER.enable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
//         OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
//     }

//     /*================================================================================================================
//      *===== PROTECTED PROPERTIES                                                                                 =====
//      *================================================================================================================*/

//     /**
//      * Map parameter
//      */
//     protected Map<String,String> params = new HashMap<String, String>();

//     /**
//      * REST Response Body: code
//      */
//     protected @Getter @Setter ResponseBodyDto<Object> responseBodyDto = new ResponseBodyDto<Object>();

//     /**
//      * Logger
//      */
//     protected final Logger logger = Logger.getLogger(this.getClass());

//     /*================================================================================================================
//      *===== PUBLIC METHODS                                                                                       =====
//      *================================================================================================================*/

//     @Override
//     public void addParam(String key, String value) {
//         this.params.put(key, value);
//     }

//     @Override
//     public void setParams(Map<String, String> params) {
//         this.params.putAll(params);
//     }

//     @Override
//     public Map<String, String> getParams() {
//         return this.params;
//     }

//     /*================================================================================================================
//      *===== PROTECTED METHODS                                                                                    =====
//      *================================================================================================================*/

//     /**
//      * Get session custom option object
//      * @return
//      */
//     protected HashMap<String, String> getCustOptInSession() {

//         HttpSession session =  ServletActionContext.getRequest().getSession();

//         return (HashMap<String, String>) session.getAttribute("__cust_opt__");
//     }

//     /**
//      * Get session connecting VNC machine list permission
//      * @return
//      */
//     protected String getConnectVncMachineListPermission() {

//         HttpSession session =  ServletActionContext.getRequest().getSession();

//         return (String) session.getAttribute("ADMN_AUTH_KEY_RMS_MACHINELIST_CONNECTVNC");
//     }

//     /**
//      * Get parameter DTO object
//      *
//      * @param type  DTO type
//      *
//      * @return  Parameter DTO Object
//      */
//     protected <T extends Object> T getParametersDto(Class<T> type) {

//         // Declare map data
//         Map<String, Object> dataMap = new HashMap<>();

//         // Get request parameters
//         HttpServletRequest request = ServletActionContext.getRequest();
//         Map<String, String[]> parametersMap = request.getParameterMap();

//         // Put from request parameter into data map
//         if (MapUtils.isNotEmpty(parametersMap)) {
//             for (String key: parametersMap.keySet()) {

//                 Field field = FieldUtils.getField(type, key, true);

//                 if (field == null) {
//                     continue;
//                 }

//                 // Adjust date time data with time zone
//                 // FIXME

//                 if (field.getType().isArray()) {
//                     dataMap.put(key, parametersMap.get(key));
//                     continue;
//                 }

//                 if (Collection.class.isAssignableFrom(field.getType())) {
//                     dataMap.put(key, parametersMap.get(key));
//                     continue;
//                 }

//                 dataMap.put(key, parametersMap.get(key)[0]);
//             }
//         }

//         // Put static param into data map
//         if (MapUtils.isNotEmpty(this.params)) {
//             for (String key: this.params.keySet()) {

//                 Field field = FieldUtils.getField(type, key, true);

//                 if (field == null) {
//                     continue;
//                 }

//                 if (field.getType().isArray()) {
//                     String[] dataArray = {this.params.get(key)};
//                     dataMap.put(key, dataArray);
//                     continue;
//                 }

//                 if (Collection.class.isAssignableFrom(field.getType())) {
//                     String[] dataArray = {this.params.get(key)};
//                     dataMap.put(key, dataArray);
//                     continue;
//                 }

//                 dataMap.put(key, this.params.get(key));
//             }
//         }

//         return OBJECT_MAPPER.convertValue(dataMap, type);
//     }


//     protected  HashMap<String, Object> getParametersHashMap() {

//         HttpServletRequest request = ServletActionContext.getRequest();
//         Map<String, String[]> params = request.getParameterMap();

//         HashMap<String, Object> data = new HashMap<>();
//         for (String key : params.keySet()) {
//             if (params.get(key).length > 1 ) {
//                 data.put(key, params.get(key));
//             } else {
//                 data.put(key, params.get(key)[0]);
//             }
//         }

//         logger.debug( data);
//       return data ;

//     }

//     public String getSessionAdminId() {

//         return SessionUtil.getSessionValue(Constants.SESSION_KEY_ADMIN_ID).toString();
//     }

//     /**
//      * Build business logic error response
//      *
//      * @param e BusinessLogicException
//      */
//     protected void buildBusinessLogicErrorResponse(BusinessLogicException e) {

//         // Change response code
//         responseBodyDto.setCode(e.getResponseCode());

//         // Change response data
//         responseBodyDto.setData(e.getResponseData());
//     }

//     /*================================================================================================================
//      *===== PRIVATE METHODS                                                                                      =====
//      *================================================================================================================*/

// }