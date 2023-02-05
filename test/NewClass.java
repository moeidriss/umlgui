
import java.io.File;

/*
 * Copyright 2023 Moe.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 *
 * @author Moe
 */
public class NewClass {
    
    public static void main(String[] s){
        StringBuffer sb = new StringBuffer();
        File f = new File("C:\\Users\\Moe\\Documents\\NetBeansProjects\\MoeUmlGui\\dist\\lib");
        for(int i=0 ; i<f.list().length ; i++){
            sb.append("lib/").append(f.list()[i]).append(";");
        }
        System.out.println(sb.toString());
    }
}
