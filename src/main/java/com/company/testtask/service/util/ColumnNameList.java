package com.company.testtask.service.util;

import java.util.ArrayList;
import java.util.List;

import static com.company.testtask.service.util.DataUtil.AMOUNT_LC;
import static com.company.testtask.service.util.DataUtil.BUN;
import static com.company.testtask.service.util.DataUtil.CRCY;
import static com.company.testtask.service.util.DataUtil.DOC_DATE;
import static com.company.testtask.service.util.DataUtil.ITEM;
import static com.company.testtask.service.util.DataUtil.MATERIAL_DESCRIPTION;
import static com.company.testtask.service.util.DataUtil.PSTG_DATE;
import static com.company.testtask.service.util.DataUtil.QUANTITY;
import static com.company.testtask.service.util.DataUtil.USER_NAME;

public class ColumnNameList {

    public static List<String> columnNames = new ArrayList<>();
    static {
        columnNames.add(DOC_DATE);
        columnNames.add(ITEM);
        columnNames.add(DOC_DATE);
        columnNames.add(PSTG_DATE);
        columnNames.add(MATERIAL_DESCRIPTION);
        columnNames.add(QUANTITY);
        columnNames.add(BUN);
        columnNames.add(AMOUNT_LC);
        columnNames.add(CRCY);
        columnNames.add(USER_NAME);
    }
}
