package net.azurewebsites.pzgccxs.pzgc.utilities;

import com.alibaba.fastjson.JSON;

public class JSONResult {
    public static String fillResultString(int status, String message, Object data){
        return JSON.toJSONString(new Result(status, message, data));
    }

    static class Result{
        private int status;
        private String message;
        private Object data;

        public Result(int status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
