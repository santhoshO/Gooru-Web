/*******************************************************************************
 * Copyright 2013 Ednovo d/b/a Gooru. All rights reserved.
 * 
 *  http://www.goorulearning.org/
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining
 *  a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including
 *  without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *  LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *  OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.drive;

import java.util.List;

import org.ednovo.gooru.client.gin.BaseViewWithHandlers;
import org.ednovo.gooru.shared.model.drive.DriveDo;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Search Team ` *
 */
public class DriveView extends BaseViewWithHandlers<DriveUiHandlers> implements
		IsDriveView, MessageProperties {

	@UiField
	HTMLPanel panelDriveBreadCrums, panelFileList;

	private static DriveViewUiBinder uiBinder = GWT
			.create(DriveViewUiBinder.class);

	interface DriveViewUiBinder extends UiBinder<Widget, DriveView> {
	}

	public DriveView() {
		setWidget(uiBinder.createAndBindUi(this));
//		rootDriveLabel.setText("Drive");
		panelDriveBreadCrums.add(new Label("Drive > "));
	}

	
	
	
	@Override
	public void setInSlot(Object slot, Widget content) {

	}

	@Override
	public void getDriveDetails(DriveDo driveDo) {
		// TODO Auto-generated method stub
		panelFileList.clear();
		panelFileList.add(new GoogleDocsResourceView(driveDo));

	}

//	@UiHandler("rootDriveLabel")
//	public void rootDriveLabelclick(ClickEvent event) {
//		getUiHandlers().getdriveListAgain();
//
//	}

	@Override
	public void getFolderDetails(String title, String id, List<DriveDo> result) {
		// TODO Auto-generated method stub
		panelDriveBreadCrums.add(new Label(" "+title+""));
		panelFileList.clear();
		for (int n = 0; n < result.size(); n++) {
			panelFileList.add(new GoogleWebResource(result.get(n)));

		}
	}

	@Override
	public void driveContentList(List<DriveDo> result) {
		// TODO Auto-generated method stub
		panelFileList.clear();

		for (int n = 0; n < result.size(); n++) {
			panelFileList.add(new GoogleWebResource(result.get(n)));

		}
	}

}