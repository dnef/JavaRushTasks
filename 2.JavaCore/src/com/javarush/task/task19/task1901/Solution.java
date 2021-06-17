package com.javarush.task.task19.task1901;

/* 
TableAdapter
*/

public class Solution {
    public interface ATable {
        String getCurrentUserName();

        String getTableName();
    }

    public interface BTable {
        String getHeaderText();
    }

    public static void main(String[] args) {
        //это пример вывода
        ATable aTable = new ATable() {
            @Override
            public String getCurrentUserName() {
                return "Amigo";
            }

            @Override
            public String getTableName() {
                return "DashboardTable";
            }
        };

        BTable table = new TableAdapter(aTable);
        System.out.println(table.getHeaderText());
    }

    private static class TableAdapter implements BTable {
        private ATable aTable;
        public TableAdapter(ATable aTable) {
            this.aTable = aTable;
        }

        @Override
        public String getHeaderText() {
            return "["+aTable.getCurrentUserName()+"]"+" : "+aTable.getTableName();
        }
    }
}