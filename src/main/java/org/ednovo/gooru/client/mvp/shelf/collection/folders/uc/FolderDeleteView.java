package org.ednovo.gooru.client.mvp.shelf.collection.folders.uc;

import java.util.HashMap;
import java.util.Map;

import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.folders.event.RefreshFolderType;
import org.ednovo.gooru.client.mvp.shelf.collection.folders.events.RefreshFolderItemEvent;
import org.ednovo.gooru.client.uc.AppPopUp;
import org.ednovo.gooru.client.uc.TextBoxWithPlaceholder;
import org.ednovo.gooru.shared.model.folder.FolderDo;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Search Team
 * 
 */
public class FolderDeleteView extends AppPopUp implements MessageProperties{

	private static FolderDeleteViewUiBinder uiBinder = GWT
			.create(FolderDeleteViewUiBinder.class);

	interface FolderDeleteViewUiBinder extends
			UiBinder<Widget, FolderDeleteView> {
	}
	@UiField Label titleLabel,headerLabel,descriptionLabel,deleteText;
	@UiField Button cancelButton,okButton;
	@UiField TextBoxWithPlaceholder txtDelete;
	
	private static String DELETE = "delete";
	
	private String parentId;
	
	private static final String O1_LEVEL = "o1";
	
	private static final String O2_LEVEL = "o2";
	
	private static final String O3_LEVEL = "o3";

	public FolderDeleteView() {
		if(AppClientFactory.getPlaceManager().getRequestParameter(O3_LEVEL)!=null){
			parentId = AppClientFactory.getPlaceManager().getRequestParameter(O3_LEVEL);
		} else if(AppClientFactory.getPlaceManager().getRequestParameter(O2_LEVEL)!=null){
			parentId = AppClientFactory.getPlaceManager().getRequestParameter(O2_LEVEL);
		} else {
			parentId = AppClientFactory.getPlaceManager().getRequestParameter(O1_LEVEL);
		}
		setWidget(uiBinder.createAndBindUi(this)); 
		setView();
		
		
	}
	/**
	 * @function setview
	 * 
	 * @created_date : 06-Feb-2014
	 * 
	 * @description
	 * 
	 * @parm(s) : 
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	*/
	public void setView()
	{
		titleLabel.setText(GL1176);
		cancelButton.setText(GL0142);
		okButton.setText(GL0190);
		headerLabel.setText(GL1174);
		descriptionLabel.setText(GL1328);
		txtDelete.setPlaceholder(GL1175);
		txtDelete.addKeyUpHandler(new ValidateConfirmText());
		okButton.getElement().addClassName("disabled");
		deleteText.setText(GL0560);
		deleteText.setVisible(false);
	}
	/**
	 * @function close popup
	 * 
	 * @created_date : 06-Feb-2014
	 * 
	 * @description
	 * 
	 * @parm(s) : 
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	*/
	@UiHandler("cancelButton")
	public void onClickOfCancelButton(ClickEvent event){
		hide();	
		Window.enableScrolling(true);
	}
	
	/**
	 * @function display success popup
	 * 
	 * @created_date : 06-Feb-2014
	 * 
	 * @description
	 * 
	 * @parm(s) : 
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	*/
	@UiHandler("okButton")
	public void onClickOfokButton(ClickEvent event){
		if (txtDelete.getText().trim().equalsIgnoreCase(DELETE)) {
			onTextConfirmed();
		}
	}
	
	

	/**
	 * key handler to validate action text
	 * 
	 * @author BLR Team
	 * 
	 */
	private class ValidateConfirmText implements KeyUpHandler {

		@Override
		public void onKeyUp(KeyUpEvent event) {
			okButton.getElement().addClassName("disabled");
			okButton.setEnabled(false);
			if (txtDelete.getText().trim().equalsIgnoreCase(DELETE)) {
				okButton.getElement().removeClassName("disabled");
				okButton.setEnabled(true);
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					onTextConfirmed();
				}
			}
		}

		
	}
	/**
	 * @function onTextConfirmed
	 * 
	 * @created_date : 06-Feb-2014
	 * 
	 * @description call folder delete api in text confirm
	 * 
	 * @parm(s) : 
	 * 
	 * @return : void
	 *
	 * @throws : <Mentioned if any exceptions>
	*/
	private void onTextConfirmed() {
		deleteText.setVisible(true);
		okButton.setVisible(false);
		cancelButton.setVisible(false);
		AppClientFactory.getInjector().getfolderService().deleteCollectionsFolder(parentId, new SimpleAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				okButton.setVisible(true);
				cancelButton.setVisible(true);
				deleteText.setVisible(false);
				FolderDo folderDo = new FolderDo();
				folderDo.setGooruOid(parentId);
				AppClientFactory.fireEvent(new RefreshFolderItemEvent(folderDo, RefreshFolderType.DELETE, null));
				DeleteFolderSuccessView deleteFolderSuccessView=new DeleteFolderSuccessView() {
					@Override
					public void onClickPositiveButton(ClickEvent event) {
						Window.enableScrolling(true);
						appPopUp.hide();
					}
				};
				hide();
				getPreviousPlace();
			}
		});
	}
	
	private void getPreviousPlace() {
		final String o1 = AppClientFactory.getPlaceManager().getRequestParameter(O1_LEVEL);
		final String o2 = AppClientFactory.getPlaceManager().getRequestParameter(O2_LEVEL);
		final String o3 = AppClientFactory.getPlaceManager().getRequestParameter(O3_LEVEL);
		
		Map<String,String> params = new HashMap<String,String>();
		
		if(o3!=null) {
			params.put(O1_LEVEL, o1);
			params.put(O2_LEVEL, o2);
		} else if(o2!=null) {
			params.put(O1_LEVEL, o1);
		}
		
		AppClientFactory.getPlaceManager().revealPlace(PlaceTokens.SHELF, params);
	}
	
}