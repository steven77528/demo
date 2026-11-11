package com.flk.demo;

import util.DBUtil;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

/**
 * 把图片或文件保存到数据库中
 *
 * @author 刘彦民 2015年9月20日 上午11:46:56
 */
public class Main {

    /**
     * 保存方法
     *
     * @param filename 文件名称
     */
    public static void saveImgOrFile(String filename) throws Exception {
        FileInputStream in = new FileInputStream(filename);
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
//            String sql = "insert into testPng(id, pic) values(seq_testPng_id.NEXTVAL, ?)";
            String sql = "INSERT INTO test(id, pic) VALUES(seq_test.nextval, ?);";
            ps = conn.prepareStatement(sql);
            ps.setBinaryStream(1, in, in.available());
            ps.execute();
            System.out.println("保存成功！");
        } catch (Exception e) {
            System.out.println("保存文件失败！");
            throw e;
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }


    public static void importFile() {
        int count = 0;
        try {
            do {
                Random rand = new Random();
                int n = rand.nextInt(390) + 1;
                System.out.print(n);
//                String filename = "D:\\文件\\CAT\\" + n + ".jpg";
                String filename = "/usr/local/CAT/" + n + ".jpg";
                saveImgOrFile(filename);
                count++;
            } while (count < 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}