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
package moe.umlgui.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import moe.umlgui.model.*;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
/**
 *
 * @author Moe
 */
public class ReportEngine {
    
    public static File pptReport(UmlModel model) throws IOException{
        XMLSlideShow ppt = new XMLSlideShow();
        XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
        
        //title slide
        XSLFSlide titleSlide = ppt.createSlide(defaultMaster.getLayout(SlideLayout.TITLE));
        titleSlide.getPlaceholder(0).setText(model.getName());
        titleSlide.getPlaceholder(1).setText("BUSINESS ANALYSIS");
        
        //overview
        XSLFSlide ovSlide = ppt.createSlide();
        //indented text with custom bullets
        
        //diagrams recursively
            //diagram -> attachmentOwner -> diagram
        for(UmlDiagram diagram : model.getDiagrams()){
            //slide for image
            //possible slide for overview if attachment owners present
        }
            
        
        //biz objects / controllers 
        
        
        //diagram inventory
        
        File f = File.createTempFile(model.getName() , ".ppt");
        FileOutputStream fos = new FileOutputStream(f);
        ppt.write(fos);
        fos.close();
        return f;
    }
    
}
