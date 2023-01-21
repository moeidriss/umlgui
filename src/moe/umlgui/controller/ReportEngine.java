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

import java.awt.Rectangle;
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
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
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
            pptxAddDiagramOverviewBullet(diagram , sp , 0);
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
            for(CoreObject bo : model.getProject().getBusinessObjects()){
                pptAddBusinessObjectSlide(bo , ppt);
            }
        }
        
        //diagram inventory
        
        File f = File.createTempFile(model.getName() , ".pptx");
        FileOutputStream fos = new FileOutputStream(f);
        ppt.write(fos);
        fos.close();
        return f;
    }
    
    private static void pptAddBusinessObjectSlide(CoreObject bo , XMLSlideShow ppt){
        XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
        XSLFSlide s = ppt.createSlide(defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT));
        s.getPlaceholder(0).setText(bo.getName());
        
        int numRows = Math.max(bo.getProperties().size(), bo.getMethods().size()) + 2;
        
        XSLFTable t = s.createTable();
        XSLFTableRow r;
        XSLFTableCell c;
        
        //H1
        //prop          |   meth
        /*r = t.addRow();        
        c = r.addCell();    t.getCell(0, 0).setText("Properties");
        c = r.addCell();    c = r.addCell();    t.getCell(0,2).setText("Methods");
        c = r.addCell();        c = r.addCell();    
        t.mergeCells(0, 0, 0, 1);
        t.mergeCells(0, 0, 2, 4);*/
        
        r = t.addRow();
        c = r.addCell();    t.getCell(0, 0).setText("Name");
        c = r.addCell();    t.getCell(0, 1).setText("Data Type");
        c = r.addCell();    t.getCell(0, 2).setText("Name");
        c = r.addCell();    t.getCell(0, 3).setText("Return Type");
        c = r.addCell();    t.getCell(0, 4).setText("Arguments");
        
        //H2
        //name  dType   |   name    rType   args
        r = t.addRow();
        c = r.addCell();    t.getCell(1, 0).setText("Name");
        c = r.addCell();    t.getCell(1, 1).setText("Data Type");
        c = r.addCell();    t.getCell(1, 2).setText("Name");
        c = r.addCell();    t.getCell(1, 3).setText("Return Type");
        c = r.addCell();    t.getCell(1, 4).setText("Arguments");
        
        /*
        //DATA
        for(int i=0 ; i<numRows-3 ; i++){
            r = t.addRow();
            if(i < bo.getProperties().size()){
                c = r.addCell();
                t.getCell(i+2, 0).setText(((BusinessObjectProperty)bo.getProperties().get(i)).getName());
                
                c = r.addCell();
                t.getCell(i+2, 1).setText(((BusinessObjectProperty)bo.getProperties().get(i)).getDataType());
            }
            if(i < bo.getProperties().size()){
                c = r.addCell();
                t.getCell(i+2, 2).setText(((BusinessObjectMethod)bo.getMethods().get(i)).getName());
                
                c = r.addCell();
                t.getCell(i+2, 3).setText(((BusinessObjectMethod)bo.getMethods().get(i)).getReturnType());
                
                c = r.addCell();
                t.getCell(i+2, 4).setText(((BusinessObjectMethod)bo.getMethods().get(i)).getParameters().toString());
            }
        }
       */
        java.lang.System.out.println("table:"+t.getNumberOfRows() +","+t.getNumberOfColumns());
    }
    
    
    private static void pptxAddDiagramSlides(UmlDiagram diagram , XMLSlideShow ppt){
        if(diagram.getImage()==null)    return;//skip empty diagrams
        
        XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
        
        //slide for image
        XSLFSlide slide = ppt.createSlide(defaultMaster.getLayout(SlideLayout.TITLE_ONLY));
        slide.getPlaceholder(0).setText(diagram.getName());
        XSLFPictureData pd = ppt.addPicture(diagram.getImage(), PictureData.PictureType.PNG);
        
        XSLFPictureShape ps = slide.createPicture(pd);
        ps.setAnchor(new Rectangle(100,100,100,100));
        //possible slide for overview if attachment owners present
        if(!diagram.getAttachmentOwnerList().isEmpty()){
            //sec header: 
            
        
            for(AttachmentOwner el : diagram.getAttachmentOwnerList()){
               XSLFSlide ovSlide = ppt.createSlide(defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT));
               ovSlide.getPlaceholder(0).setText(diagram.getName() + " Spec");
                XSLFTextShape sp = ovSlide.getPlaceholder(1);
                sp.clearText();
                pptxAddAttachmentOwnerOverviewBullet(el ,sp,0);

                for(UmlDiagram d : el.getAttachedDiagrams()){
                    pptxAddDiagramSlides(d , ppt);
                }
            }
            
        }
    }
    
    private static void pptxAddDiagramOverviewBullet(UmlDiagram diagram , XSLFTextShape sp, int indent){
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
                    pptxAddDiagramOverviewBullet(d , sp , indent);
                    indent++;
                }
            }
        }
    }
    
    
    
    private static void pptxAddAttachmentOwnerOverviewBullet(AttachmentOwner at , XSLFTextShape sp, int indent){
        XSLFTextParagraph pgh = sp.addNewTextParagraph();
        pgh.setBullet(true);
        pgh.setIndentLevel(indent);
        XSLFTextRun r = pgh.addNewTextRun();
        r.setText(((UmlElement)at).getName());
        indent++;
        
        for(UmlDiagram d : at.getAttachedDiagrams()){
            XSLFTextParagraph pgh2 = sp.addNewTextParagraph();
            pgh2.setIndentLevel(indent);
            XSLFTextRun rr = pgh2.addNewTextRun();
            rr.setText(d.toString());
            indent++;
            
            if(!d.getAttachmentOwnerList().isEmpty()){
                for(AttachmentOwner a : d.getAttachmentOwnerList()){
                    pptxAddAttachmentOwnerOverviewBullet(a , sp , indent);
                    indent++;
                }
            }
        }
        
    }
    
}
