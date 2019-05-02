package com.team.hellochat.net.web.servlet;

import com.team.hellochat.net.entity.User;
import com.team.hellochat.net.service.Impl.UserServiceImpl;
import com.team.hellochat.net.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		this.userService = new UserServiceImpl();

		User user = new User();
		
		System.out.println();

		if(req.getParameter("u_id")!=null)user.setU_ID(Integer.valueOf(req.getParameter("u_id")));
		System.out.println(user.getU_ID());
		if(req.getParameter("u_nickName")!=null)user.setU_nickName(req.getParameter("u_nickName"));
		if(req.getParameter("u_passWord")!=null)user.setU_passWord(req.getParameter("u_passWord"));
		if(req.getParameter("u_signaTure")!=null)user.setU_signaTure(req.getParameter("u_signaTure"));
		if(req.getParameter("u_sex")!=null)user.setU_sex(Byte.parseByte(req.getParameter("u_sex")));
		try {
			if(req.getParameter("u_birthday")!=null)user.setU_birthday(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("u_birthday")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(req.getParameter("u_telephone")!=null)user.setU_telephone(req.getParameter("u_telephone"));
		if(req.getParameter("u_name")!=null)user.setU_name(req.getParameter("u_name"));
		if(req.getParameter("u_email")!=null)user.setU_email(req.getParameter("u_email"));
		if(req.getParameter("u_intro")!=null)user.setU_intro(req.getParameter("u_intro"));
		if(req.getParameter("u_headPortrait")!=null)user.setU_headPortrait(req.getParameter("u_headPortrait"));
		if(req.getParameter("u_shengXiao")!=null)user.setU_shengXiao(req.getParameter("u_shengXiao"));
		if(req.getParameter("u_age")!=null)user.setU_age(Integer.valueOf(req.getParameter("u_age")));
		if(req.getParameter("u_constellation")!=null)user.setU_constellation(req.getParameter("u_constellation"));
		if(req.getParameter("u_bloodType")!=null)user.setU_bloodType(req.getParameter("u_bloodType"));
		if(req.getParameter("u_schoolTag")!=null)user.setU_schoolTag(req.getParameter("u_schoolTag"));
		if(req.getParameter("u_vocation")!=null)user.setU_vocation(req.getParameter("u_vocation"));
		if(req.getParameter("u_nationID")!=null)user.setU_nationID(Integer.valueOf(req.getParameter("u_nationID")));
		if(req.getParameter("u_provinceID")!=null)user.setU_provinceID(Integer.valueOf(req.getParameter("u_provinceID")));
		if(req.getParameter("u_cityID")!=null)user.setU_cityID(Integer.valueOf(req.getParameter("u_cityID")));
		if(req.getParameter("u_userStateID")!=null)user.setU_userStateID(Integer.valueOf(req.getParameter("u_userStateID")));
	
		String type = req.getParameter("type");
		PrintWriter pw = resp.getWriter();
		switch (type) {
		case "login":
			pw.write(userService.verifyPwd(user)+"");
			break;
		case "reg":
			if(userService.insertUser(user)!=-1)
				pw.write("注册成功");
			else
				pw.write("注册失败");
			break;
		case "changePwd":
			if(userService.changePwd(user)!=-1)
				pw.write("密码修改成功");
			else
				pw.write("密码修改失败");
			break;
		case "update":
			if(userService.updateUserById(user)!=-1)
				pw.write("资料修改成功");
			else
				pw.write("资料修改失败");
			break;
		case "select":
			pw.write(userService.selectUserById(user.getU_ID()).toJson());
			break;

		default:
			break;
		}
		resp.getWriter().write("返回获取参数" + req.getParameter("password"));
		System.out.println(req.getParameter("password"));
		System.out.println("post");
		
	}
}
