/**
 * 
 */
package org.ednovo.gooru.client.mvp.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.addTagesPopup.AddTagesPopupView;
import org.ednovo.gooru.client.mvp.shelf.collection.tab.collaborators.vc.SuccessPopupViewVc;
import org.ednovo.gooru.client.uc.DownToolTipWidgetUc;
import org.ednovo.gooru.client.uc.PlayerBundle;
import org.ednovo.gooru.client.uc.StandardSgItemVc;
import org.ednovo.gooru.client.uc.ToolTipPopUp;
import org.ednovo.gooru.shared.model.code.CodeDo;
import org.ednovo.gooru.shared.model.content.CollectionItemDo;
import org.ednovo.gooru.shared.model.content.LicenseDo;
import org.ednovo.gooru.shared.model.content.customFieldValuesDO;
import org.ednovo.gooru.shared.model.search.ResourceSearchResultDo;
import org.ednovo.gooru.shared.util.InfoUtil;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author mitraJ
 *
 */
public class SearchInfoWidget extends Composite implements MessageProperties{

	private static SearchInfoWidgetUiBinder uiBinder = GWT
			.create(SearchInfoWidgetUiBinder.class);

	interface SearchInfoWidgetUiBinder extends
	UiBinder<Widget, SearchInfoWidget> {
	}

	@UiField Label lblPublisher,publisherText,courseText,legalText,
	standardsText,gradeTitle,gradesText,originalUrlTitle,adsTitle,mbFriendlyLbl,
	mbFriendlyText,dataTypeLbl,dataTypeFormat,interactiveLbl,interactiveType,eduAllignLbl,eduAllignType,eduUseLbl,
	eduRoleLbl,eduRoleType,ageRangeLbl,ageRangeType,dKnowledgeLbl,readingLevelLbl,
	hasAdaptationType,hasAdaptationLbl,languageLbl,languageType,countryCodeLbl,countryCodeType,isAdaptationLbl,isAdaptationType,
	copyRightType,copyRightLbl,hostType,hostLbl,accessibilityAPILbl,controlType,controlLbl,
	acessHazardlLbl,acessHazardType,mediaFeatureLbl,accessModelLbl,accesibilityLbl,generalLbl,
	thumbnailText,licenceCodeLbl,licenceCodeType,educationallLbl,resourceInfoLbl,
	authorLbl,authorName,adsName,schLevelLbl,schLevelInfo,keywordsTitle,
	momentsoflearningLbl,resourceTypeImage;

	@UiField HTMLPanel rightsLogoContainer,courseInfo,originalUrlText,publisherPanel,coursePanel,gradesPanel,
	adsPanel,mobileFriendlyPanel,dataTypePanel,interactivityTypePanel,eduAllignPanel,eduUsePanel,eduRolePanel,ageRangePanel,dKnowledgePanel,
	readingLevelPanel,hasAdaptationPanel,languagePanel,countryCodePanel,isAdaptationPanel,copyRightPanel,hostPanel,
	accessibilityAPIPanel,accessibilityPanel,controlPanel,accessHazardPanel,mediaFeaturePanel,accessModePanel,thumbnailPanel,licenceCodePanel,
	authorPanel,schLevelPanel,eduUseType,keyWordsPanel,keywordsInfo,readingLevelType,accessModeType,mediaFeatureType,accessibilityAPIType,dKnowledgeType,
	momentsoflearningPanel,momentsoflearningType,thumbnailurlValue;

	@UiField FlowPanel licenceContainer,standardsInfoConatiner;

	@UiField HTMLPanel standardsContentContainer,loadingImagePanel;
	@UiField Label standaInfo;

	@UiField Button addTagsBtn;

	AddTagesPopupView popup;

	ToolTipPopUp toolTip ; 

	public static final String STANDARD_CODE = "code";
	public static final String STANDARD_DESCRIPTION = "description";
	public static final String IPAD_FRIENDLY="iPad_friendly";

	private ResourceSearchResultDo searchResultDo;
	private CollectionItemDo collectionItemDo;

	@UiField HTML lblcollectionName;

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public SearchInfoWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		PlayerBundle.INSTANCE.getPlayerStyle().ensureInjected();
		setResourceInfoData();
	}

	private void setResourceInfoData() {
		publisherText.setText(GL0566);
		courseText.setText(GL0616);
		legalText.setText(GL0730+ ""+GL_SPL_SEMICOLON);
		standardsText.setText(GL0619);
		//		resourceInfoText.setText(GL0621);
		gradeTitle.setText(GL0325+ ""+GL_SPL_SEMICOLON);
		originalUrlTitle.setText(GL0976+ ""+GL_SPL_SEMICOLON);
		generalLbl.setText(GL1708);
		hostLbl.setText(GL1700+GL_SPL_SEMICOLON);
		legalText.setText(GL0730);
		
		//Educational static data
		educationallLbl.setText(GL1720);
		eduAllignLbl.setText(GL1690+GL_SPL_SEMICOLON);
		eduUseLbl.setText(GL1664+GL_SPL_SEMICOLON);
		eduRoleLbl.setText("Educational Role:");
		interactiveLbl.setText(GL1689+GL_SPL_SEMICOLON);
		ageRangeLbl.setText(GL1692+GL_SPL_SEMICOLON);
		momentsoflearningLbl.setText(GL1678+GL_SPL_SEMICOLON);
		readingLevelLbl.setText(GL1694+GL_SPL_SEMICOLON);
		schLevelLbl.setText("School Level"+GL_SPL_SEMICOLON);
		
		//Resource Info Static data
		resourceInfoLbl.setText(GL1716);
//		dateCreatedLbl.setText(GL1717+GL_SPL_SEMICOLON);
		countryCodeLbl.setText(GL1697+GL_SPL_SEMICOLON);
		languageLbl.setText(GL1696+GL_SPL_SEMICOLON);
		dataTypeLbl.setText(GL1688+GL_SPL_SEMICOLON);
		authorLbl.setText(GL0573+GL_SPL_SEMICOLON);
		copyRightLbl.setText(GL1699+GL_SPL_SEMICOLON);
		keywordsTitle.setText("Keywords"+GL_SPL_SEMICOLON);
		adsTitle.setText(GL1800+GL_SPL_SEMICOLON);
		//Accessibility Static data
		accesibilityLbl.setText(GL1703);
		mbFriendlyLbl.setText(GL1687+GL_SPL_SEMICOLON);
		accessModelLbl.setText(GL1707+GL_SPL_SEMICOLON);
		mediaFeatureLbl.setText(GL1706+GL_SPL_SEMICOLON);
		controlLbl.setText(GL1704+GL_SPL_SEMICOLON);
		acessHazardlLbl.setText(GL1705+GL_SPL_SEMICOLON);
		mbFriendlyLbl.setText(GL1687+GL_SPL_SEMICOLON);
		
	}

	/**
	 * Assign {@link ResourceSearchResultDo} instance
	 * 
	 * @param searchResultDo
	 *            instance of {@link ResourceSearchResultDo}
	 *//*
	public void setData(ResourceSearchResultDo searchResultDo) {
		this.searchResultDo = searchResultDo;
	}*/

	public void setData(ResourceSearchResultDo searchResultsDo) {
		this.searchResultDo=searchResultsDo;
		if(searchResultsDo.getResourceFormat()!=null){
			setResourceTypeImage(searchResultsDo.getResourceFormat().getValue());
		}
		if(searchResultsDo.getResourceTitle()!=null){
			lblcollectionName.setHTML(removeHtmlTags(searchResultsDo.getResourceTitle()));

		}
		if(searchResultsDo.getGrade()!=null)
		{
			gradesText.setText(searchResultsDo.getGrade());
		}
	}

	public void setResourceTypeImage(String resourceType){
		if(resourceType!=null){
			resourceType=resourceType.toLowerCase();
			if(resourceType.equalsIgnoreCase("lesson")||resourceType.equalsIgnoreCase("textbook")||resourceType.equalsIgnoreCase("handout"))
			{
				resourceType=resourceType.replaceAll("lesson", "Text").replaceAll("textbook", "Text").replaceAll("handout", "Text");
			}
			if(resourceType.equalsIgnoreCase("slide"))
			{
				resourceType=resourceType.replaceAll("slide","Image");
			}
			if(resourceType.equalsIgnoreCase("exam")||resourceType.equalsIgnoreCase("challenge")||resourceType.equalsIgnoreCase("website"))
			{
				resourceType=resourceType.replaceAll("exam","Webpage").replaceAll("challenge", "Webpage").replaceAll("website", "Webpage");
			}
			//lblresourceType.setText(resourceType);
			resourceTypeImage.setStyleName(getResourceTypeImage(resourceType));
		}
	}

	private String removeHtmlTags(String html){
		html = html.replaceAll("</p>", " ").replaceAll("<p>", "").replaceAll("<br data-mce-bogus=\"1\">", "").replaceAll("<br>", "").replaceAll("</br>", "");
		return html;
	}

	public String getResourceTypeImage(String resourceType){
		if(resourceType.equalsIgnoreCase("Video")||resourceType.equalsIgnoreCase("Videos")){
			return PlayerBundle.INSTANCE.getPlayerStyle().videoResourceTypeInfo();
		}else if(resourceType.equalsIgnoreCase("Interactive")){
			return PlayerBundle.INSTANCE.getPlayerStyle().interactiveResourceTypeInfo();
		}
		else if(resourceType.equalsIgnoreCase("Website")||resourceType.equalsIgnoreCase("Webpage")){
			return PlayerBundle.INSTANCE.getPlayerStyle().websiteResourceTypeInfo();		
		}
		else if(resourceType.equalsIgnoreCase("Slide")){
			return PlayerBundle.INSTANCE.getPlayerStyle().imageResourceTypeInfo();
		}
		else if(resourceType.equalsIgnoreCase("Textbook")){
			return PlayerBundle.INSTANCE.getPlayerStyle().textResourceTypeInfo();
		}
		else if(resourceType.equalsIgnoreCase("Question")){
			return PlayerBundle.INSTANCE.getPlayerStyle().questionResourceTypeInfo();
		}
		else if(resourceType.equalsIgnoreCase("lesson")){
			return PlayerBundle.INSTANCE.getPlayerStyle().lessonResourceTypeInfo();

		}else if(resourceType.equalsIgnoreCase("Handout")){
			return PlayerBundle.INSTANCE.getPlayerStyle().textResourceTypeInfo();
		} else if(resourceType.equalsIgnoreCase("text")){
			return PlayerBundle.INSTANCE.getPlayerStyle().textResourceTypeInfo();
		}
		else if(resourceType.equalsIgnoreCase("image")){
			return PlayerBundle.INSTANCE.getPlayerStyle().imageResourceTypeInfo();
		}
		else if(resourceType.equalsIgnoreCase("audio")){
			return PlayerBundle.INSTANCE.getPlayerStyle().audioResourceTypeInfo();
		}else if(resourceType.equalsIgnoreCase("exam")){
			return PlayerBundle.INSTANCE.getPlayerStyle().websiteResourceTypeInfo();
		}
		else {
			return PlayerBundle.INSTANCE.getPlayerStyle().otherResourceTypeInfo();
		}
	}

	/**
	 * @param collectionItemDo
	 *            instance of {@link collectionItemDo}
	 * 
	 */

	public void setData(CollectionItemDo collectionItemDo) {
		this.collectionItemDo =collectionItemDo;
	}

	public void setData(){
		AppClientFactory.getInjector().getPlayerAppService().getResourceCollectionItem(null, searchResultDo.getGooruOid(), null, new SimpleAsyncCallback<CollectionItemDo>() {

			@Override
			public void onSuccess(CollectionItemDo collectionItemDo) {
				
				getLoadingImagePanel().setVisible(false);
				setGrades(collectionItemDo.getResource().getGrade());
				setResourceAttribution(collectionItemDo.getResource().getResourceSource()!=null?collectionItemDo.getResource().getResourceSource().getAttribution():
					null,collectionItemDo.getResource().getTaxonomySet());
				renderStandards(standardsInfoConatiner,collectionItemDo.getStandards());
				setOriginalUrl(collectionItemDo.getResource().getAssetURI(),collectionItemDo.getResource().getFolder(),
						collectionItemDo.getResource().getUrl(),collectionItemDo.getResource().getResourceType().getName());
				setResourceLicenceLogo(collectionItemDo.getResource().getAssetURI(), collectionItemDo.getResource().getLicense());
				setPublisher(collectionItemDo.getResource().getResourceSource()!=null?collectionItemDo.getResource().getResourceSource().getAttribution():"",collectionItemDo.getResource().getUrl());
				if(collectionItemDo.getResource().getThumbnails()!=null){
					setThumbnailUrl(collectionItemDo.getResource().getThumbnails().getUrl());
				}
				/*if(collectionItemDo.getResource().getCreatedOn()!=null){
					setCreatedDate(collectionItemDo.getResource().getCreatedOn());
				}*/
                setGeneralResourceInfo(collectionItemDo);
				setEducationInfoDetails(collectionItemDo);
				setResourceInfoDetails(collectionItemDo);
				setAccessibilityDetails(collectionItemDo);
				
			}
		});
	}


	protected void setGeneralResourceInfo(CollectionItemDo CollectiongenealInfo) {
		
		hostPanel.setVisible(true);
		if(CollectiongenealInfo.getResource()!=null && CollectiongenealInfo.getResource().getCustomFieldValues()!=null &&
				CollectiongenealInfo.getResource().getCustomFieldValues().getCfHost()!=null){
			hostType.setText(CollectiongenealInfo.getResource().getCustomFieldValues().getCfHost());
		}else{
			hostPanel.setVisible(false);
		}
	}

	/**
	 * To set Resource License
	 * @param assetUrl 
	 * @param licenseDo instance of {@link LicenseDo}
	 */
	public void setResourceLicenceLogo(String assetUrl,LicenseDo licenseDo){
		if(licenseDo!=null){
			if(licenseDo.getIcon()!=null&&!licenseDo.getIcon().trim().equals("")){
				Image image=new Image();
				image.setUrl(assetUrl+licenseDo.getIcon());
				image.addMouseOverHandler(new MouseOverShowStandardToolTip(licenseDo.getCode()+"         "+licenseDo.getName()));
				image.addMouseOutHandler(new MouseOutHideToolTip());
				licenceContainer.setVisible(true);
				rightsLogoContainer.clear();
				rightsLogoContainer.add(image);
			}
			else{
				licenceContainer.setVisible(false);
				rightsLogoContainer.clear();
			}
		}else{
			licenceContainer.setVisible(false);
			rightsLogoContainer.clear();
		}		
	}	


	public class MouseOverShowStandardToolTip implements MouseOverHandler
	{
		String desc=null;

		public MouseOverShowStandardToolTip(String description) {
			this.desc = description;
		}

		@Override
		public void onMouseOver(MouseOverEvent event) {
			toolTip = new ToolTipPopUp(desc, (event.getRelativeElement().getAbsoluteLeft()-83),(event.getRelativeElement().getAbsoluteTop()+22));
			toolTip.setStyleName("");
			toolTip.show();
			toolTip.getElement().getStyle().setZIndex(99999);
		}
	}

	public class MouseOutHideToolTip implements MouseOutHandler
	{
		@Override
		public void onMouseOut(MouseOutEvent event) {
			toolTip.hide();
		}

	}
	/**
	 * To set Resource Grades
	 * @param gradesText  {@value String}
	 */
	public void setGrades(String gradesText){

		if(gradesText!=null&&!gradesText.equalsIgnoreCase("")&&!gradesText.equalsIgnoreCase("null")){
			this.gradesText.setText(InfoUtil.getGrades(gradesText));
			gradesPanel.setVisible(true);
		}else{
			gradesPanel.setVisible(false);
		}
	}
	
	/**
	 * To show Standards Information
	 * @param standardsContainer
	 * @param standardsList
	 */

	public void renderStandards(FlowPanel standardsContainer, List<Map<String,String>> standardsList) {
		standardsContainer.clear();

		if (standardsList != null) {
			standaInfo.setVisible(false);
			standardsContentContainer.setVisible(true);
			Iterator<Map<String, String>> iterator = standardsList.iterator();
			int count = 0;
			FlowPanel toolTipwidgets = new FlowPanel();
			while (iterator.hasNext()) {
				Map<String, String> standard = iterator.next();
				String stdCode = standard.get(STANDARD_CODE);
				String stdDec = standard.get(STANDARD_DESCRIPTION);
				if (count > 2) {
					if (count < 18){
						StandardSgItemVc standardItem = new StandardSgItemVc(stdCode, stdDec);
						toolTipwidgets.add(standardItem);
					}
				} else {
					DownToolTipWidgetUc toolTipUc = new DownToolTipWidgetUc(new Label(stdCode), new Label(stdDec), standardsList);
					toolTipUc.setStyleName(PlayerBundle.INSTANCE.getPlayerStyle().getstandardMoreInfo());
					standardsContainer.add(toolTipUc);
				}
				count++;
			}
			if (standardsList.size()>18){
				final Label left = new Label("+"+(standardsList.size() - 18));
				toolTipwidgets.add(left);
				standardsContentContainer.setVisible(true);
			}
			if (standardsList.size() > 2) {
				Integer moreStandardsCount = standardsList.size() - 3;
				if (moreStandardsCount >0){
					DownToolTipWidgetUc toolTipUc = new DownToolTipWidgetUc(new Label("+" + moreStandardsCount), toolTipwidgets, standardsList);
					toolTipUc.setStyleName(PlayerBundle.INSTANCE.getPlayerStyle().getstandardMoreLink());
					standardsContainer.add(toolTipUc);
					standardsContentContainer.setVisible(true);
				}
			}
			if(standardsList.isEmpty())
			{
				standardsContentContainer.setVisible(false);
			}
		}
		else{
			standardsContentContainer.setVisible(false);
		}
	}
	/**
	 * To set the Resource Original Url
	 * @param assetUri
	 * @param folder
	 * @param originalUrl
	 * @param resourceTypeName
	 */
	public void setOriginalUrl(String assetUri,String folder,String originalUrl,String resourceTypeName){
		this.originalUrlText.clear();
		if(originalUrl!=null&&!originalUrl.equalsIgnoreCase("")&&!originalUrl.equalsIgnoreCase("null")){
			if(resourceTypeName.equalsIgnoreCase("image/png")){
				if(!originalUrl.substring(0, 4).equalsIgnoreCase("http")){
					originalUrl=assetUri+folder+originalUrl;
				}
			}
			String[] urlFormat = originalUrl.split("\\.");
			String urlExtension = urlFormat[urlFormat.length - 1];
			if(urlExtension.equalsIgnoreCase("pdf")){
				if(!originalUrl.substring(0, 4).equalsIgnoreCase("http")){
					originalUrl=assetUri+folder+originalUrl;
				}
			}
			Anchor originalUrlAnchor=new Anchor(originalUrl);
			originalUrlAnchor.setHref(originalUrl);
			originalUrlAnchor.setStyleName("");
			originalUrlAnchor.setTarget("_blank");
			this.originalUrlText.add(originalUrlAnchor);
			this.originalUrlTitle.setVisible(true);
			this.originalUrlText.setVisible(true);
		}else{
			this.originalUrlTitle.setVisible(false);
			this.originalUrlText.setVisible(false);
		}
	}
	/**
	 * To set the Resource publisherName
	 * @param publisherName
	 * @param resourceUrl
	 */
	public void setPublisher(String publisherName,String resourceUrl){
		if(publisherName==null||publisherName.equalsIgnoreCase("")||publisherName.equalsIgnoreCase("null")){
			//lblPublisher.setText(GL0977);
			publisherPanel.setVisible(false);
		}
		else{
			if((!resourceUrl.startsWith("https://docs.google.com"))&&(!resourceUrl.startsWith("http://docs.google.com"))){
				lblPublisher.setText(publisherName);
				publisherPanel.setVisible(true);
			}
			else{
				//	lblPublisher.setText(GL0977);	
				publisherPanel.setVisible(false);
			}
		}
	}
	/**
	 * 
	 * @param url
	 */
	private void setThumbnailUrl(String url) {
		thumbnailurlValue.clear();
		if(url==null||url.equalsIgnoreCase("")||url.equalsIgnoreCase("null")){
			thumbnailPanel.setVisible(false);
		}else{
			thumbnailPanel.setVisible(false);
		}
	}
	/**
	 * 
	 * @param code
	 *//*
	private void setLicenCode(String code) {

		if(code==null||code.equalsIgnoreCase("")||code.equalsIgnoreCase("null") || code.equalsIgnoreCase("Not Available")){
			licenceCodePanel.setVisible(false);
		}else{
			licenceCodePanel.setVisible(true);
			licenceCodeType.setText(code);
			licenceCodeLbl.setText(GL1719+GL_SPL_SEMICOLON);
		}		
	}*/
	/**
	 * 
	 * @param createdOn
	 *//*
	private void setCreatedDate(Date createdOn) {

		String dateString = DateTimeFormat.getFormat("MM/dd/yyyy").format(createdOn);
		dateCreatedPanel.setVisible(true);
		createdDateInfo.setText(dateString);
		dateCreatedLbl.setText(GL1717+GL_SPL_SEMICOLON);
	}*/
	/**
	 * 
	 * @param attribution
	 * @param taxonomoyList
	 */
	public void setResourceAttribution(String attribution,Set<CodeDo> taxonomoyList){

		List<String> coursesList=new ArrayList<String>();
		if(taxonomoyList!=null){
			Iterator<CodeDo> taxonomyIterator=taxonomoyList.iterator();
			while (taxonomyIterator.hasNext()) {
				CodeDo codeDo=taxonomyIterator.next();
				if(codeDo.getDepth()==2){
					coursesList.add(codeDo.getLabel());
				}
			}

		}
		courseInfo.clear();
		if(coursesList.size()>0){
			final Label courseInfoLabel=new Label(coursesList.get(0));
			courseInfoLabel.setStyleName(PlayerBundle.INSTANCE.getPlayerStyle().resourceCourseLabel());
			courseInfo.add(courseInfoLabel);
			coursePanel.setVisible(true);
		}
		if(coursesList.size()>1){
			final Label courseCountLabel=new Label("+"+(coursesList.size()-1)); 
			courseCountLabel.setStyleName(PlayerBundle.INSTANCE.getPlayerStyle().resourceCourseNum());
			courseInfo.add(courseCountLabel);
			Widget Coursewidget = getToolTipwidgets(coursesList);
			courseCountLabel.addMouseOverHandler(new MouseOverShowToolTip(Coursewidget));
			courseCountLabel.addMouseOutHandler(new MouseOutHideToolTip());
			coursePanel.setVisible(true);

		}
		if(coursesList.size()==0){
			/*Label courseInfoLabel=new Label();
			 courseInfoLabel.setText(GL0977);
			 courseInfo.clear();
			 courseInfo.add(courseInfoLabel);*/
			coursePanel.setVisible(false);
		}

	}

	private Widget getToolTipwidgets(List<String> coursesList) {

		FlowPanel toolTipwidgets = new FlowPanel();
		for(int i=1;i<coursesList.size();i++){
			Label courseLabel = new Label(coursesList.get(i));
			toolTipwidgets.add(courseLabel);
		}
		return toolTipwidgets;
	}

	public class MouseOverShowToolTip implements MouseOverHandler
	{
		Widget widget;

		public MouseOverShowToolTip(Widget coursewidget) {
			this.widget = coursewidget;
		}

		@Override
		public void onMouseOver(MouseOverEvent event) {
			//toolTip = new ToolTipPopUp(widget,getWidget().getAbsoluteLeft() + (getWidget().getOffsetWidth() / 2) - (tooltipPopUpUc.getOffsetWidth() / 2), getWidget().getAbsoluteTop() + getWidget().getOffsetHeight());	
			toolTip = new ToolTipPopUp(widget,(event.getRelativeElement().getAbsoluteLeft()-55),(event.getRelativeElement().getAbsoluteTop()+5)); 
			toolTip.setStyleName(PlayerBundle.INSTANCE.getPlayerStyle().courseTooltip());
			toolTip.show();
		}

	}

	private void seteducationaluseDetails(List<String> eduUsedetails) {
		eduUseType.clear();
		if(eduUsedetails == null || eduUsedetails.size() == 0 || eduUsedetails.contains(null) || eduUsedetails.contains("") ){
			eduUsePanel.setVisible(false);
		}else{
			educationallLbl.setText(GL1720);
			if(eduUsedetails.size()>0){
				final Label eduUseLabel=new Label(eduUsedetails.get(0));
				eduUseLabel.getElement().setAttribute("style", "float: left;");
				eduUseType.add(eduUseLabel);
				eduUsePanel.setVisible(true);
				educationallLbl.setText(GL1720);
				eduUseLbl.setText(GL1664+GL_SPL_SEMICOLON);
				educationallLbl.setVisible(true);
			}
			if(eduUsedetails.size()>2){
				final Label eduUseLabel=new Label("+"+(eduUsedetails.size()-2)); 
				eduUseLabel.setStyleName(PlayerBundle.INSTANCE.getPlayerStyle().resourceCourseNum());
				eduUseType.add(eduUseLabel);
				Widget eduusewidget = getCommonwidget(eduUsedetails);
				eduUseLabel.addMouseOverHandler(new MouseOverShowToolTip(eduusewidget));
				eduUseLabel.addMouseOutHandler(new MouseOutHideToolTip());
				eduUsePanel.setVisible(true);
				educationallLbl.setText(GL1720);
				eduUseLbl.setText(GL1664+GL_SPL_SEMICOLON);
				educationallLbl.setVisible(true);
			}
		}
	}
	
	private Widget getCommonwidget(List<String> commonList) {

		FlowPanel toolTipwidgets = new FlowPanel();
		for(int i=2;i<commonList.size();i++){
			Label commonLabel = new Label(commonList.get(i));
			toolTipwidgets.add(commonLabel);
		}
		return toolTipwidgets;
	}

	/**
	 * To set Resource Educational details
	 * @param collectionItemDo {@link CollectionItemDo} 
	 */

	private void setEducationInfoDetails(CollectionItemDo collectionItemDo) {

		List<String> eduUsedetails = new ArrayList<String>();
		List<String> depthofknowledgedetails = new ArrayList<String>();
		List<String> momentoflearningdetails = new ArrayList<String>();
		educationallLbl.setVisible(true);
		if(collectionItemDo.getResource()!=null ){
			if(collectionItemDo.getResource().getEducationalUse()!=null){
				if(collectionItemDo.getResource().getEducationalUse().size()>0){
					for(int i=0;i<collectionItemDo.getResource().getEducationalUse().size();i++){
						if(collectionItemDo.getResource().getEducationalUse().get(i).isSelected())
						{
							eduUsedetails.add(collectionItemDo.getResource().getEducationalUse().get(i).getValue());
						}
					}
					seteducationaluseDetails(eduUsedetails);
				}else{
					eduUsePanel.setVisible(false);
				}
			}

			if(collectionItemDo.getResource().getResourceFormat().getValue().equalsIgnoreCase("question")){
				depthofknowledgedetails = new ArrayList<String>();
				if(collectionItemDo.getResource().getDepthOfKnowledges()!=null){
					if(collectionItemDo.getResource().getDepthOfKnowledges().size()>0){
						for(int i=0;i<collectionItemDo.getResource().getDepthOfKnowledges().size();i++){
							if(collectionItemDo.getResource().getDepthOfKnowledges().get(i).isSelected())
							{
								depthofknowledgedetails.add(collectionItemDo.getResource().getDepthOfKnowledges().get(i).getValue());
							}
						}
						InfoUtil.setDepthofknowledgeDetails(depthofknowledgedetails, dKnowledgeType, dKnowledgeLbl, dKnowledgePanel);
						//dKnowledgePanel.setVisible(true);
					}else{
						dKnowledgePanel.setVisible(false);
					}
				}else{
					dKnowledgePanel.setVisible(false);
				}
			}
			else{
				momentoflearningdetails = new ArrayList<String>();
				if(collectionItemDo.getResource().getMomentsOfLearning()!=null){
					if(collectionItemDo.getResource().getMomentsOfLearning().size()>0){
						for(int i=0;i<collectionItemDo.getResource().getMomentsOfLearning().size();i++){
							if(collectionItemDo.getResource().getMomentsOfLearning().get(i).isSelected())
							{
								momentoflearningdetails.add(collectionItemDo.getResource().getMomentsOfLearning().get(i).getValue());
							}
						}
						InfoUtil.setDepthofknowledgeDetails(momentoflearningdetails,momentsoflearningType,momentsoflearningLbl,momentsoflearningPanel);
						//momentsoflearningPanel.setVisible(true);
					}else{
						momentsoflearningPanel.setVisible(false);	
					}
				}else{
					momentsoflearningPanel.setVisible(false);
				}
			}
             

			if(collectionItemDo.getResource().getCustomFieldValues()!=null){

				String learningMode = collectionItemDo.getResource().getCustomFieldValues().getCfLearningMode()!=null ? collectionItemDo.getResource().getCustomFieldValues().getCfLearningMode() : null;
				String educationRole= collectionItemDo.getResource().getCustomFieldValues().getCfEndUser()!=null ? collectionItemDo.getResource().getCustomFieldValues().getCfEndUser() : null ;
				String age = collectionItemDo.getResource().getCustomFieldValues().getCfAge()!=null ? collectionItemDo.getResource().getCustomFieldValues().getCfAge() : null ;
				String readingLevel =collectionItemDo.getResource().getCustomFieldValues().getCfReadingLevel()!=null ? collectionItemDo.getResource().getCustomFieldValues().getCfReadingLevel() : null ;
				String schLevel = collectionItemDo.getResource().getCustomFieldValues().getCfSchoolLevel()!=null ? collectionItemDo.getResource().getCustomFieldValues().getCfSchoolLevel() : null ;
				String alignType = collectionItemDo.getResource().getCustomFieldValues().getCfEducationalAlignment()!=null ? collectionItemDo.getResource().getCustomFieldValues().getCfEducationalAlignment() : null;

				if(alignType!=null && !alignType.equals("")){
					eduAllignType.setText(alignType);
					eduAllignPanel.setVisible(true);
				}else{
					eduAllignPanel.setVisible(false);
				}
				if(educationRole!=null && !educationRole.equals("")){
					eduRoleType.setText(educationRole);
					eduRolePanel.setVisible(true);
				}else{
					eduRolePanel.setVisible(false);
				}
				if(learningMode!=null && !learningMode.equals("")){
					interactiveType.setText(learningMode);
					interactivityTypePanel.setVisible(true);
				}else{
					interactivityTypePanel.setVisible(false);
				}
				if(age!=null && !age.equals("")){
					ageRangeType.setText(age);
					ageRangePanel.setVisible(true);
				}else{
					ageRangePanel.setVisible(false);
				}

				if(readingLevel!=null && !readingLevel.equals("")){
					readingLevelType.getElement().setInnerText(readingLevel);
					readingLevelPanel.setVisible(true);
				}else{
					readingLevelPanel.setVisible(false);
				}

				if(schLevel!=null && !schLevel.equals("")){
					schLevelInfo.setText(schLevel);
					schLevelPanel.setVisible(true);
				}else{
					schLevelPanel.setVisible(false);
				}

				if(learningMode==null && educationRole==null && age==null && readingLevel==null && schLevel==null
						&& alignType==null && depthofknowledgedetails.isEmpty() && momentoflearningdetails.isEmpty()){
					educationallLbl.setVisible(false);
				}
			}else{
				if(depthofknowledgedetails.isEmpty() && depthofknowledgedetails.isEmpty()){
					educationallLbl.setVisible(false);
				}
			}
			
		}else{
			educationallLbl.setVisible(false);
			schLevelPanel.setVisible(false);
			readingLevelPanel.setVisible(false);
			ageRangePanel.setVisible(false);
			interactivityTypePanel.setVisible(false);
			eduRolePanel.setVisible(false);
			eduAllignPanel.setVisible(false);
			momentsoflearningPanel.setVisible(false);
			dKnowledgePanel.setVisible(false);
		}
	}
	
	/**
	 * To set Resource Information details.
	 * @param collectionInfo instance of {@link CollectionItemDo}
	 */
	
	protected void setResourceInfoDetails(CollectionItemDo collectionInfo) {
		
		if(collectionInfo.getResource()!=null && collectionInfo.getResource().getCustomFieldValues()!=null){
			resourceInfoLbl.setVisible(true);
			customFieldValuesDO customField= collectionInfo.getResource().getCustomFieldValues();
			
			String countryCode= customField.getCfCountryCode()!=null ? customField.getCfCountryCode() : null;
			String language= customField.getCfLanguageCode()!=null ? customField.getCfLanguageCode() : null;
			String dataType= customField.getCfDataType()!=null ? customField.getCfDataType() : null;
			String author= customField.getCfAuthor()!=null ? customField.getCfAuthor() : null;
			String copyrightHolder= customField.getCfCopyrightHolder()!=null ? customField.getCfCopyrightHolder() : null;
			String keywords= customField.getCfKeywords()!=null ? customField.getCfKeywords() : null;
			String ads= customField.getCfAds()!=null ? customField.getCfAds() : null;
			
			
			if(countryCode!=null && !countryCode.equals("")){
				countryCodeType.setText(countryCode);
				countryCodePanel.setVisible(true);
			}else{
				countryCodePanel.setVisible(false);
			}
			if(language!=null && !language.equals("")){
				languageType.setText(language);
				languagePanel.setVisible(true);
			}else{
				languagePanel.setVisible(false);
			}
			if(dataType!=null && !dataType.equals("")){
				dataTypeFormat.setText(dataType);
				dataTypePanel.setVisible(true);
			}else{
				dataTypePanel.setVisible(false);
			}
			if(copyrightHolder!=null && !copyrightHolder.equals("")){
				copyRightType.setText(copyrightHolder);
				copyRightPanel.setVisible(true);
			}else{
				copyRightPanel.setVisible(false);
			}
			if(author!=null && !author.equals("")){
				authorName.setText(author);
				authorPanel.setVisible(true);
			}else{
				authorPanel.setVisible(false);
			}
			if(keywords!=null && !keywords.equals("")){
				keywordsInfo.getElement().setInnerText(keywords);
				keyWordsPanel.setVisible(true);
			}else{
				keyWordsPanel.setVisible(false);
			}
			if(ads!=null && !ads.equals("")){
				adsName.setText(ads);
				adsPanel.setVisible(true);
			}else{
				adsPanel.setVisible(false);
			}
			
			if(countryCode==null && language==null && dataType==null && author==null && copyrightHolder==null
					&& keywords==null && ads==null){
				resourceInfoLbl.setVisible(false);
			}
			
		}else{
			countryCodePanel.setVisible(false);
			adsPanel.setVisible(false);
			keyWordsPanel.setVisible(false);
			authorPanel.setVisible(false);
			copyRightPanel.setVisible(false);
			dataTypePanel.setVisible(false);
			languagePanel.setVisible(false);
			resourceInfoLbl.setVisible(false);
		}
	}
	
	/**
	 * To set the Resource Accessibility details 
	 * @param collectionItem instance of {@link CollectionItemDo} 
	 */
	
	protected void setAccessibilityDetails(CollectionItemDo collectionItem) {
		
		if(collectionItem.getResource()!=null){
			accesibilityLbl.setVisible(true);
			String mobileFriendly= collectionItem.getResource().getMediaType()!=null ? collectionItem.getResource().getMediaType() : null;
			String accessMode = null,mediaFeature = null, controlFlexibility = null, accessHazard = null;
			
			if(mobileFriendly!=null && !mobileFriendly.equals("")){
				if(mobileFriendly.equalsIgnoreCase(IPAD_FRIENDLY)){
					mobileFriendlyPanel.setVisible(true);
					mbFriendlyText.setText(GL_GRR_YES);
				}else{
					mobileFriendlyPanel.setVisible(true);
					mbFriendlyText.setText(GL1735);
				}
			}else{
				mobileFriendlyPanel.setVisible(false);
			}
			
			if(collectionItem.getResource().getCustomFieldValues()!=null){
				customFieldValuesDO customFields=collectionItem.getResource().getCustomFieldValues();
				
				accessMode= customFields.getCfAccessMode()!=null ? customFields.getCfAccessMode() : null ;
				mediaFeature = customFields.getCfMediaFeature()!=null ? customFields.getCfMediaFeature() : null;
				controlFlexibility= customFields.getCfControlFlexibility()!=null ? customFields.getCfControlFlexibility() : null;
				accessHazard = customFields.getCfControlFlexibility()!=null ? customFields.getCfControlFlexibility() : null;
				
				if(accessMode!=null && !accessMode.equals("")){
					accessModeType.getElement().setInnerText(accessMode);
					accessModePanel.setVisible(true);
				}else{
					accessModePanel.setVisible(false);
				}
				if(mediaFeature!=null && !mediaFeature.equals("")){
					mediaFeatureType.getElement().setInnerText(mediaFeature);
					mediaFeaturePanel.setVisible(true);
				}else{
					mediaFeaturePanel.setVisible(false);
				}
				if(controlFlexibility!=null && !controlFlexibility.equals("")){
					controlType.setText(controlFlexibility);
					controlPanel.setVisible(true);
				}else{
					controlPanel.setVisible(false);
				}
				if(accessHazard!=null && !accessHazard.equals("")){
					acessHazardType.setText(accessHazard);
					accessHazardPanel.setVisible(true);
				}else{
					accessHazardPanel.setVisible(false);
				}
			}
			
			if(mobileFriendly==null && accessMode==null && mediaFeature==null && controlFlexibility==null && accessHazard==null){
				accesibilityLbl.setVisible(false);
			}
			 
		}else{
			accesibilityLbl.setVisible(false);
			accessHazardPanel.setVisible(false);
			controlPanel.setVisible(false);
			mediaFeaturePanel.setVisible(false);
			accessModePanel.setVisible(false);
			mobileFriendlyPanel.setVisible(false);
		}
		
	}
	
	/**
	 * @return the loadingImageLabel
	 */
	public HTMLPanel getLoadingImagePanel() {
		return loadingImagePanel;
	}

	@UiHandler("addTagsBtn")
	public void onAddTagsBtnClicked(ClickEvent clickEvent) 
	{
		Window.enableScrolling(false);
		popup=new AddTagesPopupView(searchResultDo.getGooruOid()) {

			@Override
			public void closePoup(boolean isCancelclicked) {
				 this.hide();
				 if(!isCancelclicked){
			        SuccessPopupViewVc success = new SuccessPopupViewVc() {

						@Override
						public void onClickPositiveButton(ClickEvent event) {
							this.hide();
							Window.enableScrolling(true);
						}
						
					};
					success.setHeight("253px");
					success.setWidth("450px");
					success.setPopupTitle(GL1795);
					success.setDescText(GL1796);
					success.enableTaggingImage();
					success.setPositiveButtonText(GL0190);
					success.center();
					success.show();
				 }else{
					 Window.enableScrolling(true);
				 }
			}
		};
		popup.show();
		popup.setPopupPosition(popup.getAbsoluteLeft(),Window.getScrollTop()+10);
	}


}
