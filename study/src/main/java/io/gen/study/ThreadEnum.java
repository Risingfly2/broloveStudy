package io.gen.study;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ThreadEnum {
    public enum Fu{
        G(1),
        B(2);
        private int shit;

        Fu(int shit) {
            this.shit = shit;
        }
    }
    public enum ThreadType{
        FUCK("fuck"),
        DUCK("duck"),
        DOG("dog");

        private String type;

        ThreadType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public static Map<String, ThreadType> typeMap;

        static {
            Map<String, ThreadType> type_map = new HashMap<>();
            for (ThreadType threadType: ThreadType.values()){
                type_map.put(threadType.getType(), threadType);
                System.out.println(threadType.getType()+" -> " + threadType);
            }
            typeMap = Collections.unmodifiableMap(type_map);
        }

        public static ThreadType fromType(String type){
            ThreadType threadType = typeMap.get(type);
            if (null == threadType){
                throw new IllegalArgumentException("no ThreadType for " + type);
            }
            return threadType;
        }
    }

    public static void main(String[] args) {

        System.out.println(Fu.valueOf("B"));
        System.out.println(Arrays.stream(Fu.values()).toArray().length);

        ThreadType threadType = ThreadType.valueOf(ThreadType.class, "FUCK");
        System.out.println(threadType.toString());
        System.out.println(threadType.name());
        System.out.println(threadType.ordinal());
    }
}
