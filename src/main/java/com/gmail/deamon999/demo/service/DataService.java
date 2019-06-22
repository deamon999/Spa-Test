package com.gmail.deamon999.demo.service;

import com.gmail.deamon999.demo.entity.DataModel;
import com.gmail.deamon999.demo.repository.DataModelRepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
public class DataService {

    @Autowired
    private DataModelRepo dataModelRepo;

    public ResponseEntity uploadCsvFile(MultipartFile file) {
        //Reader reader = null;
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader().withHeader("ssoid", "ts", "grp", "type", "subtype", "url", "orgid", "formid", "code", "ltpa", "sudirresponse", "ymdh")
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            List<DataModel> dataModelList = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
                String str = csvRecord.get(0);
                String[] arr = str.split(";");
                String ssoid = arr[0];
                String ts = arr[1];
                String grp = arr[2];
                String type = arr[3];
                String subtype = arr[4];
                String url = arr[5];
                String orgid = arr[6];
                String formid = arr[7];
                String code = arr[8];
                String ltpa = arr[9];
                String sudirresponse = arr[10];
                String ymdh = arr[11];
                if (ssoid != null) {
                    if (ymdh != null && ts != null) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
                        Date date = null;
                        try {
                            date = simpleDateFormat.parse(ymdh);
                        } catch (ParseException e) {
                            date = new Date();
                        }
                        long tsTime = Long.valueOf(ts);
                        dataModelList.add(new DataModel(ssoid, new Date(tsTime), grp, type, subtype, url, orgid,
                                formid, code, ltpa, sudirresponse, date));
                    } else {
                        dataModelList.add(new DataModel(ssoid, null, grp, type, subtype, url, orgid,
                                formid, code, ltpa, sudirresponse, null));
                    }
                }
            }
            dataModelRepo.saveAll(dataModelList);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity uploadXlsFile(MultipartFile file) throws IOException, ParseException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        List<DataModel> dataModelList = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            //ssoid	ts	grp	type	subtype	url	orgid	formid	code	ltpa	sudirresponse	ymdh
            String ssoid = null;
            if (row.getCell(0) != null) {
                switch (row.getCell(0).getCellType()) {
                    case STRING:
                        ssoid = row.getCell(0).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - ssoid");
                        break;
                    default:
                        continue;
                }
            }
            long ts = 0L;
            if (row.getCell(1) != null) {
                switch (row.getCell(1).getCellType()) {
                    case NUMERIC:
                        ts = (long) row.getCell(1).getNumericCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - ts");
                        break;
                }
            }
            String grp = null;
            if (row.getCell(2) != null) {
                switch (row.getCell(2).getCellType()) {
                    case STRING:
                        grp = row.getCell(2).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - grp");
                        break;
                }
            }
            String type = null;
            if (row.getCell(3) != null) {
                switch (row.getCell(3).getCellType()) {
                    case STRING:
                        type = row.getCell(3).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - type");
                        break;
                }
            }
            String subtype = null;
            if (row.getCell(4) != null) {
                switch (row.getCell(4).getCellType()) {
                    case STRING:
                        subtype = row.getCell(4).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - subtype");
                        break;
                }
            }
            String url = null;
            if (row.getCell(5) != null) {
                switch (row.getCell(5).getCellType()) {
                    case STRING:
                        url = row.getCell(5).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - url");
                        break;
                }
            }
            String orgid = null;
            if (row.getCell(6) != null) {
                switch (row.getCell(6).getCellType()) {
                    case STRING:
                        orgid = row.getCell(6).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - orgid");
                        break;
                }
            }
            String formid = null;
            if (row.getCell(7) != null) {
                switch (row.getCell(7).getCellType()) {
                    case STRING:
                        formid = row.getCell(7).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - formid");
                        break;
                }
            }
            String code = null;
            if (row.getCell(8) != null) {
                switch (row.getCell(8).getCellType()) {
                    case STRING:
                        code = row.getCell(8).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - code");
                        break;
                }
            }
            String ltpa = null;
            if (row.getCell(9) != null) {
                switch (row.getCell(9).getCellType()) {
                    case STRING:
                        ltpa = row.getCell(9).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - ltpa");
                        break;
                }
            }
            String sudirresponse = null;
            if (row.getCell(10) != null) {
                switch (row.getCell(10).getCellType()) {
                    case STRING:
                        sudirresponse = row.getCell(10).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - sudirresponse");
                        break;
                }
            }
            String ymdh = null;
            if (row.getCell(11) != null) {
                switch (row.getCell(11).getCellType()) {
                    case STRING:
                        ymdh = row.getCell(11).getStringCellValue();
                        break;
                    case BLANK:
                        System.out.println(i + "row, cell - ymdh");
                        break;
                }
            }

            if (ssoid != null) {
                if (ymdh != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
                    Date date = simpleDateFormat.parse(ymdh);
                    long tsTime = Long.valueOf(ts);
                    dataModelList.add(new DataModel(ssoid, new Date(tsTime), grp, type, subtype, url, orgid,
                            formid, code, ltpa, sudirresponse, date));
                } else {
                    dataModelList.add(new DataModel(ssoid, null, grp, type, subtype, url, orgid,
                            formid, code, ltpa, sudirresponse, null));
                }
            }
        }

        dataModelRepo.saveAll(dataModelList);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity getTopForms() {
        List<String> topForms = dataModelRepo.findDistinctformid();
        Map<String, Integer> forms = new HashMap<>();
        for (String str : topForms) {
            if (!str.isEmpty()) {
                int count = dataModelRepo.countAllByFormid(str);
                forms.put(str, count);
            }
        }
        // let's sort this map by values first
        Map<String, Integer> sorted =forms
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(5)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        return new ResponseEntity<>(sorted.entrySet(), HttpStatus.OK);
    }

    public ResponseEntity getUsers() {
        List<String> users = dataModelRepo.findDistinctSsoid();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity getForms(String id) {
        List<DataModel> userForms = dataModelRepo.getAllBySsoid(id);
        Set<String> forms = userForms.stream().map(x -> x.getFormid()).collect(Collectors.toSet());
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }
}
