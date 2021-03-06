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
import java.util.Map;

import org.ednovo.gooru.client.AppPlaceKeeper;
import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.gin.BasePlacePresenter;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.drive.event.DriveEvent;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.drive.event.DriveEventHandler;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.drive.event.FolderEvent;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.drive.event.FolderEventHandlers;
import org.ednovo.gooru.shared.model.drive.DriveDo;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class DrivePresenter extends
		BasePlacePresenter<IsDriveView, DrivePresenter.IsDriveyProxy> implements
		DriveUiHandlers {

	@ProxyCodeSplit
	@NameToken(PlaceTokens.DRIVE)
	@UseGatekeeper(AppPlaceKeeper.class)
	public interface IsDriveyProxy extends ProxyPlace<DrivePresenter> {
	}

	@Inject
	public DrivePresenter(IsDriveView view, IsDriveyProxy proxy) {
		super(view, proxy);
		getView().setUiHandlers(this);
		addRegisteredHandler(DriveEvent.TYPE, driveEvent);
		addRegisteredHandler(FolderEvent.TYPE, folderEvent);

		
	}

	@Override
	public void onBind() {
		super.onBind();
	}

	@Override
	public void onReveal() {
		super.onReveal();
		System.out.println("reveal in drive");
	}

	@Override
	public void onReset() {
		super.onReset();

	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
	}

	@Override
	public String getViewToken() {
		return PlaceTokens.HOME;
	}

	@Override
	public Map<String, Object> redirect() {
		// TODO Auto-generated method stub
		return null;
	}

	DriveEventHandler driveEvent = new DriveEventHandler() {

		@Override
		public void clearDrivepage(DriveDo driveDo) {
			getView().getDriveDetails(driveDo);
			// TODO Auto-generated method stub

		}
	};

	FolderEventHandlers folderEvent = new FolderEventHandlers() {

		@Override
		public void clearFolderpage(String title, String id,
				List<DriveDo> result) {
			// TODO Auto-generated method stub
			getView().getFolderDetails(title, id, result);

		}

	};

	@Override
	public void getdriveListAgain() {
		AppClientFactory.getInjector().getResourceService()
				.getDrive(new AsyncCallback<List<DriveDo>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(List<DriveDo> result) {
						getView().driveContentList(result);
						// TODO Auto-generated method stub

					}

				});

	}

	public void callDriveContent() {
		// TODO Auto-generated method stub
		AppClientFactory.getInjector().getResourceService()
			.getDrive(new SimpleAsyncCallback<List<DriveDo>>() {

				@Override
				public void onSuccess(List<DriveDo> result) {
					getView().driveContentList(result);	
				}
			});
	}

}