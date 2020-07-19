//package com.jinan.springcloud.controller;
//import cn.hutool.poi.excel.ExcelUtil;
//import com.jinan.springcloud.entity.User;
//import com.jinan.springcloud.service.IUserService;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class ExcelController {
//
//    @PostMapping("/upload")
//    public void upload1(MultipartFile file, @Validated UploadReq req) throws Exception {
//        //从数据库查询出现有的数据,根据去重的字段分组去构建成一个HashMap,通过containsKey()判断
//        //将需要更新的数据放到updateList中
//        List<User> updateList=new ArrayList<>();
//
//        //已取值的行数
//        int rowNum = 0;
//        //列号
//        int colNum = 0;
//        //真正有数据的行数
//        int realRowCount = 0;
//        //得到工作空间
//        Workbook workbook = null;
//
//        try {
//            workbook = ExcelUtil.getWorkbookByInputStream(file.getInputStream(), file.getOriginalFilename());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //得到工作表
//        int numberOfSheets = workbook.getNumberOfSheets();
//        for (int i = 0; i < numberOfSheets; i++) {
//            Sheet sheet = ExcelUtil.getSheetByWorkbook(workbook, i);
//            realRowCount = sheet.getPhysicalNumberOfRows();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            List<User> list = new ArrayList<>();
//            User user = null;
//
//            for(Row row:sheet) {
//                if(realRowCount == rowNum) {
//                    break;
//                }
//                //空行跳过
//                if(ExcelUtil.isBlankRow(row)) {
//                    continue;
//                }
//                if(row.getRowNum() == -1) {
//                    continue;
//                }else {
//                    //第一行表头跳过
//                    if(row.getRowNum() == 0) {
//                        continue;
//                    }
//                }
//                rowNum ++;
//                colNum = 1;
//                user = new User();
//                ExcelUtil.validCellValue(sheet, row, colNum, "id");
//                user.setId(Integer.valueOf(ExcelUtil.getCellValue(sheet, row, colNum - 1)));
//                ExcelUtil.validCellValue(sheet, row, ++ colNum, "name");
//                user.setId(Integer.valueOf(ExcelUtil.getCellValue(sheet, row, colNum - 1)));
//                //判断是否是已存在的数据,如果是就更新,不是就新增
//                //updateList.add(user);
//                list.add(user);
//
//            }
//
//            //新增的逻辑
//            IUserService.saveBatch(list);
//            System.out.println(list);
//        }
//    }
//
//}
