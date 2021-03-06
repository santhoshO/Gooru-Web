package org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.drive;

import java.util.List;

import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.drive.event.DriveEvent;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.resource.add.drive.event.FolderEvent;
import org.ednovo.gooru.shared.model.drive.DriveDo;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class GoogleWebResource extends Composite implements MessageProperties {
	private static GoogleWebResourceUiBinder uiBinder = GWT
			.create(GoogleWebResourceUiBinder.class);

	interface GoogleWebResourceUiBinder extends
			UiBinder<Widget, GoogleWebResource> {

	}

	String folderMimeType = "application/vnd.google-apps.folder";
	String type = null;

	String dataIconType = null;
	@UiField
	Label imageIcon;
	@UiField
	HTML driveText;
	@UiField
	HTMLPanel contentPanel;
	@UiField
	HTMLPanel folderContent;

	DriveDo driveDo = new DriveDo();

	public GoogleWebResource(DriveDo driveDo) {

		initWidget(uiBinder.createAndBindUi(this));
		this.driveDo = driveDo;
		System.out.println("value s enter  wideget" + driveDo.getTitle());
		driveText.setText(driveDo.getTitle());
		contentPanel.addDomHandler(contentClick, ClickEvent.getType());
		type = driveDo.getMimeType();
		String dataType[] = type.split("\\.");
		System.out.println("dataypes" + dataType[0].toString());
		dataIconType = dataType[0];

	}

	ClickHandler contentClick = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			System.out.println("datfaypeicon" + dataIconType);

			if (type.equalsIgnoreCase(folderMimeType)) {

				folderContent(driveDo.getId());

				System.out.println("enter in folder condition");

			} else {
				AppClientFactory.fireEvent(new DriveEvent(driveDo));
				System.out.println("elase condion in  widget");

			}

		}

	};

	private void folderContent(String id) {
		folderContent.clear();
		// TODO Auto-generated method stub
		AppClientFactory.getInjector().getResourceService()
				.getfolderList(id, new AsyncCallback<List<DriveDo>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(List<DriveDo> result) {

						System.out.println("enteringb   successssssss");
						AppClientFactory.fireEvent(new FolderEvent(driveDo
								.getTitle(), driveDo.getId(), result));
						// TODO Auto-generated method stub
						// System.out.println("on sucesssss"+result.get(0).getTitle());
						/*
						 * if (result != null) {
						 * 
						 * for(int m=0;m<result.size();m++){
						 * 
						 * folderContent.add(new
						 * GoogleWebResource(result.get(m)));
						 * 
						 * }
						 * 
						 * }
						 */
					}
				});

	}
}
