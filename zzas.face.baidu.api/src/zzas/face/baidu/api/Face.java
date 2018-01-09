package zzas.face.baidu.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("百度人脸识别接口")
public class Face {
	public static final String APP_ID = "10618677";
	public static final String API_KEY = "YAI5HFGl8G92gOT7ZAAKReUY";
	public static final String SECRET_KEY = "6mk31UKFEBQ2k8Q2O18zb7Ajk77eW46z";
	public static AipFace client = createAipFace();

	public static AipFace createAipFace() {
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		return client;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("人脸检测")
	public static DataObject detect(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("max_face_num", param.getString("max_face_num"));
		options.put("face_fields", param.getString("face_fields"));
		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.DetectData");

		if (param.get("image") == null) {
			res = client.detect(param.getBytes("imageBytes"), options);
		} else {
			res = client.detect(param.getString("image"), options);
		}

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}

		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));
		dataObject.set("result_num", res.get("result_num"));

		JSONArray result = res.getJSONArray("result");
		for (int i = 0; i < result.length(); i++) {
			JSONObject element = result.getJSONObject(i);
			dataObject.set("result[" + (i + 1) + "]/age", element.get("age"));
			dataObject.set("result[" + (i + 1) + "]/beauty", element.get("beauty"));

			JSONObject location = element.getJSONObject("location");
			dataObject.set("result[" + (i + 1) + "]/location/left", location.get("left"));
			dataObject.set("result[" + (i + 1) + "]/location/top", location.get("top"));
			dataObject.set("result[" + (i + 1) + "]/location/width", location.get("width"));
			dataObject.set("result[" + (i + 1) + "]/location/height", location.get("height"));

			dataObject.set("result[" + (i + 1) + "]/face_probability",
					element.get("face_probability"));
			dataObject.set("result[" + (i + 1) + "]/rotation_angle", element.get("rotation_angle"));
			dataObject.set("result[" + (i + 1) + "]/yaw", element.get("yaw"));
			dataObject.set("result[" + (i + 1) + "]/pitch", element.get("pitch"));
			dataObject.set("result[" + (i + 1) + "]/roll", element.get("roll"));
			dataObject.set("result[" + (i + 1) + "]/expression", element.get("expression"));
			dataObject.set("result[" + (i + 1) + "]/expression_probability",
					element.get("expression_probablity"));

			JSONArray faceshape = element.getJSONArray("faceshape");
			for (int j = 0; j < faceshape.length(); j++) {
				JSONObject faceshape_element = faceshape.getJSONObject(j);
				dataObject.set("result[" + (i + 1) + "]/faceshape[" + (j + 1) + "]/type",
						faceshape_element.get("type"));
				dataObject.set("result[" + (i + 1) + "]/faceshape[" + (j + 1) + "]/probability",
						faceshape_element.get("probability"));
			}

			dataObject.set("result[" + (i + 1) + "]/gender", element.get("gender"));
			dataObject.set("result[" + (i + 1) + "]/gender_probability",
					element.get("gender_probability"));
			dataObject.set("result[" + (i + 1) + "]/glasses", element.get("glasses"));
			dataObject.set("result[" + (i + 1) + "]/glasses_probability",
					element.get("glasses_probability"));

			JSONArray landmark = element.getJSONArray("landmark");
			for (int j = 0; j < landmark.length(); j++) {
				JSONObject landmark_element = landmark.getJSONObject(j);
				dataObject.set("result[" + (i + 1) + "]/landmark[" + (j + 1) + "]/x",
						landmark_element.get("x"));
				dataObject.set("result[" + (i + 1) + "]/landmark[" + (j + 1) + "]/y",
						landmark_element.get("y"));
			}

			JSONArray landmark72 = element.getJSONArray("landmark72");
			for (int j = 0; j < landmark72.length(); j++) {
				JSONObject landmark72_element = landmark72.getJSONObject(j);
				dataObject.set("result[" + (i + 1) + "]/landmark72[" + (j + 1) + "]/x",
						landmark72_element.get("x"));
				dataObject.set("result[" + (i + 1) + "]/landmark72[" + (j + 1) + "]/y",
						landmark72_element.get("y"));
			}

			dataObject.set("result[" + (i + 1) + "]/race", element.get("race"));
			dataObject.set("result[" + (i + 1) + "]/race_probability",
					element.get("race_probability"));

			JSONObject qualities = element.getJSONObject("qualities");

			JSONObject occlusion = qualities.getJSONObject("occlusion");
			dataObject.set("result[" + (i + 1) + "]/qualities/occlusion/left_eye",
					occlusion.get("left_eye"));
			dataObject.set("result[" + (i + 1) + "]/qualities/occlusion/right_eye",
					occlusion.get("right_eye"));
			dataObject.set("result[" + (i + 1) + "]/qualities/occlusion/nose",
					occlusion.get("nose"));
			dataObject.set("result[" + (i + 1) + "]/qualities/occlusion/mouth",
					occlusion.get("mouth"));
			dataObject.set("result[" + (i + 1) + "]/qualities/occlusion/left_cheek",
					occlusion.get("left_cheek"));
			dataObject.set("result[" + (i + 1) + "]/qualities/occlusion/right_cheek",
					occlusion.get("right_cheek"));
			dataObject.set("result[" + (i + 1) + "]/qualities/occlusion/chin",
					occlusion.get("chin"));

			dataObject.set("result[" + (i + 1) + "]/qualities/blur", qualities.get("blur"));
			dataObject.set("result[" + (i + 1) + "]/qualities/illumination",
					qualities.get("illumination"));
			dataObject.set("result[" + (i + 1) + "]/qualities/completeness",
					qualities.get("completeness"));

			JSONObject type = qualities.getJSONObject("type");
			dataObject.set("result[" + (i + 1) + "]/qualities/type/human", type.get("human"));
			dataObject.set("result[" + (i + 1) + "]/qualities/type/cartoon", type.get("cartoon"));
		}

		return dataObject;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("人脸比对")
	public static DataObject match(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("ext_fields", param.getString("ext_fields"));
		options.put("image_liveness", param.getString("image_liveness"));
		options.put("types", param.getString("types"));

		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.MatchData");

		if (param.get("image1") == null) {
			byte[][] bytes = new byte[2][];
			bytes[0] = param.getBytes("imageBytes1");
			bytes[1] = param.getBytes("imageBytes2");
			res = client.match(bytes, options);
		} else {
			List<String> lst = new ArrayList<String>();
			lst.add(param.getString("image1"));
			lst.add(param.getString("image2"));
			res = client.match(lst, options);
		}

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}

		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));
		dataObject.set("result_num", res.get("result_num"));

		JSONArray result = res.getJSONArray("result");
		for (int i = 0; i < result.length(); i++) {
			JSONObject result_element = result.getJSONObject(i);
			dataObject.set("result[" + (i + 1) + "]/index_i", result_element.get("index_i"));
			dataObject.set("result[" + (i + 1) + "]/index_j", result_element.get("index_j"));
			dataObject.set("result[" + (i + 1) + "]/score", result_element.get("score"));
		}

		JSONObject ext_info = res.getJSONObject("ext_info");
		dataObject.set("ext_info/faceliveness", ext_info.get("faceliveness"));
		dataObject.set("ext_info/qualities", ext_info.get("qualities"));

		return dataObject;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("人脸识别")
	public static DataObject identifyUser(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("ext_fields", param.getString("ext_fields"));
		options.put("user_top_num", param.getString("user_top_num"));

		String group_id = param.getString("group_id");

		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.IdentifyUserData");

		if (param.get("image") == null) {
			byte[] bytes = param.getBytes("imageBytes");
			res = client.identifyUser(group_id, bytes, options);
		} else {
			String image = param.getString("image");
			res = client.identifyUser(group_id, image, options);
		}

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}

		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));
		dataObject.set("result_num", res.get("result_num"));

		JSONObject ext_info = res.getJSONObject("ext_info");
		dataObject.set("ext_info/faceliveness", ext_info.get("faceliveness"));

		JSONArray result = res.getJSONArray("result");
		for (int i = 0; i < result.length(); i++) {
			JSONObject result_element = result.getJSONObject(i);
			dataObject.set("result[" + (i + 1) + "]/group_id", result_element.get("group_id"));
			dataObject.set("result[" + (i + 1) + "]/uid", result_element.get("uid"));
			dataObject.set("result[" + (i + 1) + "]/user_info", result_element.get("user_info"));

			JSONArray scores = result_element.getJSONArray("scores");
			for (int j = 0; j < result.length(); j++) {
				dataObject.set("result[" + (i + 1) + "]/scores[" + (j + 1) + "]", scores.get(j));
			}
		}

		return dataObject;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("人脸认证")
	public static DataObject verifyUser(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("top_num", param.getString("top_num"));
		options.put("ext_fields", param.getString("ext_fields"));

		String group_id = param.getString("group_id");
		String uid = param.getString("uid");

		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.VerifyUserData");

		if (param.get("image") == null) {
			byte[] bytes = param.getBytes("imageBytes");
			res = client.verifyUser(uid, group_id, bytes, options);
		} else {
			String image = param.getString("image");
			res = client.verifyUser(uid, group_id, image, options);
		}

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}

		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));
		dataObject.set("result_num", res.get("result_num"));

		JSONObject ext_info = res.getJSONObject("ext_info");
		dataObject.set("ext_info/faceliveness", ext_info.get("faceliveness"));

		JSONArray result = res.getJSONArray("result");
		for (int i = 0; i < result.length(); i++) {
			dataObject.set("result[" + (i + 1) + "]", result.get(i));
		}

		return dataObject;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("人脸注册")
	public static DataObject addUser(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("action_type", param.getString("action_type"));

		String group_id = param.getString("group_id");
		String uid = param.getString("uid");
		String user_info = param.getString("user_info");

		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.AddUserData");

		if (param.get("image") == null) {
			byte[] bytes = param.getBytes("imageBytes");
			res = client.addUser(uid, user_info, group_id, bytes, options);
		} else {
			String image = param.getString("image");
			res = client.addUser(uid, user_info, group_id, image, options);
		}

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}

		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));

		return dataObject;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("人脸更新")
	public static DataObject updateUser(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("action_type", param.getString("action_type"));

		String group_id = param.getString("group_id");
		String uid = param.getString("uid");
		String user_info = param.getString("user_info");

		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.UpdateUserData");

		if (param.get("image") == null) {
			byte[] bytes = param.getBytes("imageBytes");
			res = client.updateUser(uid, user_info, group_id, bytes, options);
		} else {
			String image = param.getString("image");
			res = client.updateUser(uid, user_info, group_id, image, options);
		}

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}

		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));

		return dataObject;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("人脸删除")
	public static DataObject deleteUser(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("group_id", param.getString("group_id"));

		String uid = param.getString("uid");

		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.DeleteUserData");

		res = client.deleteUser(uid, options);

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}

		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));

		return dataObject;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("用户信息查询")
	public static DataObject getUser(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("group_id", param.getString("group_id"));

		String uid = param.getString("uid");

		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.GetUserData");

		res = client.getUser(uid, options);

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}

		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));
		dataObject.set("result_num", res.get("result_num"));

		JSONArray result = res.getJSONArray("result");
		for (int i = 0; i < result.length(); i++) {
			JSONObject result_element = result.getJSONObject(i);
			dataObject.set("result[" + (i + 1) + "]/group_id", result_element.get("group_id"));
			dataObject.set("result[" + (i + 1) + "]/uid", result_element.get("uid"));
			dataObject.set("result[" + (i + 1) + "]/user_info", result_element.get("user_info"));
		}

		return dataObject;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("组列表查询")
	public static DataObject getGroupList(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("start", param.getString("start"));
		options.put("end", param.getString("end"));

		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.GetGroupListData");

		res = client.getGroupList(options);

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}

		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));
		dataObject.set("result_num", res.get("result_num"));

		JSONArray result = res.getJSONArray("result");
		for (int i = 0; i < result.length(); i++) {
			dataObject.set("result[" + (i + 1) + "]", result.get(i));
		}

		return dataObject;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("组内用户列表查询")
	public static DataObject getGroupUsers(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("start", param.getString("start"));
		options.put("end", param.getString("end"));

		String group_id = param.getString("group_id");

		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.GetGroupUsersData");

		res = client.getGroupUsers(group_id, options);

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}

		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));
		dataObject.set("result_num", res.get("result_num"));

		JSONArray result = res.getJSONArray("result");
		for (int i = 0; i < result.length(); i++) {
			JSONObject result_element = result.getJSONObject(i);
			dataObject.set("result[" + (i + 1) + "]/uid", result_element.get("uid"));
			dataObject.set("result[" + (i + 1) + "]/user_info", result_element.get("user_info"));
		}

		return dataObject;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("组间复制用户")
	public static DataObject addGroupUser(DataObject param) {
		HashMap<String, String> options = new HashMap<String, String>();
		
		String src_group_id = param.getString("src_group_id");
		String group_id = param.getString("group_id");
		String uid = param.getString("uid");
		
		JSONObject res = null;
		DataObject dataObject = DataObjectUtil
				.createDataObject("zzas.face.baidu.api.face_dataset.AddGroupUserData");

		res = client.addGroupUser(src_group_id,group_id,uid, options);

		if (res.has("error_code")) {
			dataObject.set("error_code", res.get("error_code"));
			dataObject.set("error_msg", res.get("error_msg"));

			return dataObject;
		}
		
		dataObject.set("error_code", "0");

		dataObject.set("log_id", res.get("log_id"));
		
		return dataObject;
	}
}
