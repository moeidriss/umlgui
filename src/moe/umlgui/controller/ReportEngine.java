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
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
/**
 *
 * @author Moe
 */
public class ReportEngine {
    
    public static File pptxReport(UmlModel model) throws IOException{
        XMLSlideShow ppt = new XMLSlideShow();
        XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
        
        //title slide
        XSLFSlide titleSlide = ppt.createSlide(defaultMaster.getLayout(SlideLayout.TITLE));
        titleSlide.getPlaceholder(0).setText(model.getName());
        titleSlide.getPlaceholder(1).setText("BUSINESS ANALYSIS");
        
        //overview
        XSLFSlide ovSlide = ppt.createSlide(defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT));
        ovSlide.getPlaceholder(0).setText("Overview");
        
        //indented text with custom bullets
        XSLFTextShape sp = ovSlide.getPlaceholder(1);
        sp.clearText();
        
        for(UmlDiagram diagram : model.getDiagrams()){
            pptxAddOverviewBullet(diagram , sp , 0);
        }
        
        //diagrams recursively
            //diagram -> attachmentOwner -> diagram
        for(UmlDiagram diagram : model.getDiagrams()){
            pptxAddDiagramSlides(diagram , ppt);
        }
            
        
        //biz objects / controllers 
        {
            XSLFSlide ts = ppt.createSlide(defaultMaster.getLayout(SlideLayout.SECTION_HEADER));
            ts.getPlaceholder(0).setText("Business Objects");
            
            //TODO BO in model??
            for(BusinessObject bo : model.getProject().getBusinessObjects()){
                XSLFSlide s = ppt.createSlide(defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT));
                s.getPlaceholder(0).setText(bo.getName());
            }
        }
        
        //diagram inventory
        
        File f = File.createTempFile(model.getName() , ".pptx");
        FileOutputStream fos = new FileOutputStream(f);
        ppt.write(fos);
        fos.close();
        return f;
    }
    
    private static void pptxAddDiagramSlides(UmlDiagram diagram , XMLSlideShow ppt){
        if(diagram.getImage()==null)    return;//skip empty diagrams
        
        XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
        
        //slide for image
        XSLFSlide slide = ppt.createSlide(defaultMaster.getLayout(SlideLayout.TITLE));
        slide.getPlaceholder(0).setText(diagram.getName());
        XSLFPictureData pd = ppt.addPicture(diagram.getImage(), PictureData.PictureType.PNG);
        XSLFPictureShape ps = slide.createPicture(pd);
        
        //possible slide for overview if attachment owners present
        if(!diagram.getAttachmentOwnerList().isEmpty()){
            //overview
        
            for(AttachmentOwner el : diagram.getAttachmentOwnerList()){
                //...
                
                for(UmlDiagram d : el.getAttachedDiagrams()){
                    pptxAddDiagramSlides(d , ppt);
                }
            }
            
        }
    }
    
    private static void pptxAddOverviewBullet(UmlDiagram diagram , XSLFTextShape sp, int indent){
        XSLFTextParagraph pgh = sp.addNewTextParagraph();
        pgh.setBullet(true);
        pgh.setIndentLevel(indent);
        XSLFTextRun r = pgh.addNewTextRun();
        r.setText(diagram.getName());
        indent++;
        
        if(!diagram.getAttachmentOwnerList().isEmpty()){
            for(AttachmentOwner el : diagram.getAttachmentOwnerList()){
                XSLFTextParagraph pgh2 = sp.addNewTextParagraph();
                pgh2.setIndentLevel(indent);
                XSLFTextRun rr = pgh2.addNewTextRun();
                rr.setText(el.toString());
                indent++;
                for(UmlDiagram d : el.getAttachedDiagrams()){
                    pptxAddOverviewBullet(d , sp , indent);
                    indent++;
                }
            }
        }
    }
    
}
