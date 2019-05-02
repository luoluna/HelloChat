package com.team.hellochat.net.web.servlet;

import com.team.hellochat.net.entity.Friend;
import com.team.hellochat.net.service.FriendService;
import com.team.hellochat.net.service.Impl.FriendServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FriendServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FriendService friendService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		this.friendService = new FriendServiceImpl();

		Friend friend = new Friend();
		if (req.getParameter("f_id") != null)
			friend.setF_id(Integer.valueOf(req.getParameter("f_id")));
		if (req.getParameter("f_userID") != null)
			friend.setF_userID(Integer.valueOf(req.getParameter("f_userID")));
		if (req.getParameter("f_firendID") != null)
			friend.setF_firendID(Integer.valueOf(req.getParameter("f_firendID")));
		if (req.getParameter("f_name") != null)
			friend.setF_name(req.getParameter("f_name"));
		if (req.getParameter("f_friendType") != null)
			friend.setF_friendType(Integer.valueOf(req.getParameter("f_friendType")));
		if (req.getParameter("f_friendGroups") != null)
			friend.setF_friendGroups(req.getParameter("f_friendGroups"));
		else
			friend.setF_friendGroups("我的好友");
		if (req.getParameter("f_friendState") != null)
			friend.setF_friendState(Integer.valueOf(req.getParameter("f_friendState")));
		try {
			if (req.getParameter("f_createTime") != null)
				friend.setF_createTime(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("f_createTime")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter pw = resp.getWriter();
		String type = req.getParameter("type");
		switch (type) {
		case "add":
			if (friendService.insertFriend(friend) != -1) {
				pw.write("添加成功");
			} else {
				pw.write("添加好友失败");
			}
			break;
		case "delete":
			if (friendService.deleteFriendById(friend) != -1) {
				System.out.println("删除好友成功");
			} else {
				System.out.println("删除好友失败");
			}
			break;
		case "update":
			if (friendService.updateFriendById(friend) != -1)
				System.out.println("好友信息更新成功");
			else
				System.out.println("好友信息更新失败");
			break;
		case "select":
			HashMap<String, List<Friend>> hm = friendService.selectUserById(friend.getF_userID());
			Set<Entry<String, List<Friend>>> set = hm.entrySet();
			StringBuffer str = new StringBuffer("{");
			for (Entry<String, List<Friend>> e : set) {
				str.append("\"" + e.getKey() + "\":[");
				List<Friend> list = e.getValue();
				for (int i = 0; i < list.size(); i++) {
					str.append(list.get(i).toJson());
					if (i != (list.size() - 1))
						str.append(",");
				}
				str.append("],");
			}
			str.deleteCharAt(str.length() - 1);
			str.append("}");
			pw.write(str.toString());
		default:
			break;
		}
	}
}
