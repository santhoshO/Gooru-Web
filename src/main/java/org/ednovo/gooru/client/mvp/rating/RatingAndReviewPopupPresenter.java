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
package org.ednovo.gooru.client.mvp.rating;



import java.util.ArrayList;

import org.ednovo.gooru.client.SimpleAsyncCallback;
import org.ednovo.gooru.client.gin.AppClientFactory;
import org.ednovo.gooru.client.mvp.rating.events.UpdateRatingsGraphEvent;
import org.ednovo.gooru.shared.model.content.ContentStarRatingsDo;
import org.ednovo.gooru.shared.model.content.StarRatingsDo;
import org.ednovo.gooru.shared.model.search.ResourceSearchResultDo;

import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.PresenterWidget;

public class RatingAndReviewPopupPresenter extends PresenterWidget<IsRatingAndReviewPopupView> implements RatingAndReviewPopupUiHandlers{

	@Inject
	public RatingAndReviewPopupPresenter(EventBus eventBus, IsRatingAndReviewPopupView view) {
		super(eventBus, view);
		getView().setUiHandlers(this);
		addRegisteredHandler(UpdateRatingsGraphEvent.TYPE, this);
	}
	
	public void displayPopup(String resourceTitle, String gooruOid,String createrName) {
		getView().displayPopUp(resourceTitle, gooruOid,createrName);
	}

	@Override
	public void getAverageRatingForContent(String resourceId) {
		AppClientFactory.getInjector().getPlayerAppService().getContentStarRatings(resourceId, new SimpleAsyncCallback<ContentStarRatingsDo>() {

			@Override
			public void onSuccess(ContentStarRatingsDo result) {
				getView().setGraphAndAvgContentRating(result);
			}
			
		});
	}

	@Override
	public void getUserRatingsReviews(String resourceId,int offSet) {
		AppClientFactory.getInjector().getPlayerAppService().getResourceRatingWithReviews(resourceId,null,offSet, new SimpleAsyncCallback<ArrayList<StarRatingsDo>>() {

			@Override
			public void onSuccess(ArrayList<StarRatingsDo> result) {
				getView().setUserRatingsAndReviews(result);
			}
			
		});
			
	}

	@Override
	public void updateRatingGraph(String gooruOId) {
		getView().getAverageRatingForContent(gooruOId);
	}
}
