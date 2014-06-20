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
/**
 * 
*/
package org.ednovo.gooru.client.mvp.library.sausd;

import org.ednovo.gooru.client.PlaceTokens;
import org.ednovo.gooru.shared.util.MessageProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;


/**
 * @fileName : SausdBannerView.java
 *
 * @description : 
 *
 *
 * @version : 1.0
 *
 * @date: 02-Dec-2013
 *
 * @Author IBC
 *
 * @Reviewer: 
 */
public class SausdBannerView extends Composite implements MessageProperties{

	@UiField HTMLPanel partnerLogo, landingBannerInner, findLbl, shareLbl, measureLbl, contributeLbl, fourSteps;
	@UiField Label headerTag;
	@UiField Label subHeaderTag;
	@UiField SausdStyleBundle sausdStyleUc;
	
	private static SausdBannerViewUiBinder uiBinder = GWT
			.create(SausdBannerViewUiBinder.class);

	interface SausdBannerViewUiBinder extends
			UiBinder<Widget, SausdBannerView> {
	}

	public SausdBannerView(String placeToken) {
		initWidget(uiBinder.createAndBindUi(this));
		getLandingBannerText(placeToken);
	}

	@Override
	public void onLoad() {
		landingBannerInner.getElement().setId("landingBannerInner");
	}
	
	private void getLandingBannerText(String placeToken) {
		if(placeToken.contains(PlaceTokens.SAUSD_LIBRARY)) {
			setLandingBannerText(GL1902,GL1903,GL1904,GL1905,GL1906,GL1907,GL1908,GL1909,GL1910,GL1911);
			fourSteps.getElement().getStyle().setBackgroundColor("#000000");
			fourSteps.setVisible(false);
			partnerLogo.setStyleName(sausdStyleUc.sausdPartnerLogo());
			partnerLogo.setVisible(true);
		}
	}
	
	private void setLandingBannerText(String headerMsg, String subHeaderMsg, String findInlineMsg, String findMsg, String shareInlineMsg, String shareMsg, 
			String measureInlineMsg, String measureMsg, String contributeInlineMsg, String contributeMsg) {
			headerTag.setText(headerMsg);
			subHeaderTag.setText(subHeaderMsg);
			
			InlineLabel findInlineLbl = new InlineLabel(findInlineMsg);
			InlineLabel shareInlineLbl = new InlineLabel(shareInlineMsg);
			InlineLabel measureInlineLbl = new InlineLabel(measureInlineMsg);
			InlineLabel contributeInlineLbl = new InlineLabel(contributeInlineMsg);
			
			Label findLblMsg = new Label(findMsg);
			Label shareLblMsg = new Label(shareMsg);
			Label measureLblMsg = new Label(measureMsg);
			Label contributeLblMsg = new Label(contributeMsg);
			findLblMsg.setStyleName(sausdStyleUc.bannerSpanBlock());
			shareLblMsg.setStyleName(sausdStyleUc.bannerSpanBlock());
			measureLblMsg.setStyleName(sausdStyleUc.bannerSpanBlock());
			contributeLblMsg.setStyleName(sausdStyleUc.bannerSpanBlock());
			
			findLbl.add(findInlineLbl);
			findLbl.add(findLblMsg);
			
			shareLbl.add(shareInlineLbl);
			shareLbl.add(shareLblMsg);
			
			measureLbl.add(measureInlineLbl);
			measureLbl.add(measureLblMsg);
			
			contributeLbl.add(contributeInlineLbl);
			contributeLbl.add(contributeLblMsg);
	}	
}