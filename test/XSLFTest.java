
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

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
public class XSLFTest {
    
    public static void main(String[] ss){
        try{
            File file= new File("C:\\Users\\Moe\\Documents\\tests\\slideshow.pptx");
            FileOutputStream fos = new FileOutputStream(file);
            XMLSlideShow ppt = new XMLSlideShow();
            // first see what slide layouts are available :
            System.out.println("Available slide layouts:");
            for(XSLFSlideMaster master : ppt.getSlideMasters()){
                for(XSLFSlideLayout layout : master.getSlideLayouts()){
                    System.out.println(layout.getType());
                    XSLFSlide slide1 = ppt.createSlide(layout);
                    if(slide1.getPlaceholders().length > 0){
                        XSLFTextShape title1 = slide1.getPlaceholder(0);
                        if(title1!=null)    title1.setText(layout.getType().name());  
                    }
                }
            }
            
            /*
            // blank slide
            XSLFSlide blankSlide = ppt.createSlide();
            // there can be multiple masters each referencing a number of layouts
            // for demonstration purposes we use the first (default) slide master
            XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
            // title slide
            XSLFSlideLayout titleLayout = defaultMaster.getLayout(SlideLayout.TITLE);
            // fill the placeholders
            XSLFSlide slide1 = ppt.createSlide(titleLayout);
            XSLFTextShape title1 = slide1.getPlaceholder(0);
            title1.setText("First Title");  
            // title and content
            XSLFSlideLayout titleBodyLayout = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
            XSLFSlide slide2 = ppt.createSlide(titleBodyLayout);
            XSLFTextShape title2 = slide2.getPlaceholder(0);
            title2.setText("Second Title");
            XSLFTextShape body2 = slide2.getPlaceholder(1);
            body2.clearText(); // unset any existing text
            body2.addNewTextParagraph().addNewTextRun().setText("First paragraph");
            body2.addNewTextParagraph().addNewTextRun().setText("Second paragraph");
            body2.addNewTextParagraph().addNewTextRun().setText("Third paragraph");
            */
            
            ppt.write(fos);
            fos.close();
            
            Desktop.getDesktop().open(file);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
