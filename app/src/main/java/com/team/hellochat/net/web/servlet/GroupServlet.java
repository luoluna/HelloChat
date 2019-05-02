package com.team.hellochat.net.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.hellochat.net.entity.Group;
import com.team.hellochat.net.service.ChatGroupInfoServices;
import com.team.hellochat.net.service.GroupService;
import com.team.hellochat.net.service.Impl.ChatGroupInfoServicesImpl;
import com.team.hellochat.net.service.Impl.GroupServiceImpl;
import com.team.hellochat.net.service.Impl.UserServiceImpl;
import com.team.hellochat.net.service.UserService;

public class GroupServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		GroupService groupService = new GroupServiceImpl();
		ChatGroupInfoServices chatGroupInfoServices = new ChatGroupInfoServicesImpl();
		UserService userService = new UserServiceImpl();
		
		Group g = new Group();
		if (req.getParameter("c_id") != null)g.setC_id(Integer.valueOf(req.getParameter("c_id")));
		if (req.getParameter("u_id") != null)g.setU_id(Integer.valueOf(req.getParameter("u_id")));
		if (req.getParameter("c_name") != null)g.setC_name(req.getParameter("c_name"));
		if (req.getParameter("c_number") != null)g.setC_number(Integer.valueOf(req.getParameter("c_number")));
		if (req.getParameter("c_describle") != null)g.setC_describle(req.getParameter("c_describle"));
		if (req.getParameter("c_createTime") != null)
			try {
				g.setC_createTime(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("f_createTime")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		String type = req.getParameter("type");
		PrintWriter pw = resp.getWriter();
		switch (type) {
		case "newGroup":
			pw.write(groupService.insertGroup(g));
			break;
		case "addUser":
			pw.write(chatGroupInfoServices.addGroup(g.getC_id(), g.getU_id()));
			break;
		case "removeUser":
			pw.write(chatGroupInfoServices.removeGroup(g.getC_id(), g.getU_id()));
			break;
		case "selectUsers":
			List<Integer> list = chatGroupInfoServices.usersGroup(g.getC_id());
			pw.write('[');
			for (int i = 0; i < list.size(); i++) {
				pw.write(userService.selectUserById(list.get(i)).toJson());
				if((i+1)!=list.size()) {
					pw.write(',');
				}
			}
			pw.write(']');

		default:
			break;
		}
	
	}
	
	

}
