import Promise from 'promise';
import {dashboard} from './mocks';

const log = false;

export const REQUEST_DASHBOARD = 'REQUEST_DASHBOARD';
export const RECEIVE_DASHBOARD = 'RECEIVE_DASHBOARD';

export const requestDashboard = () => ({
	type: REQUEST_DASHBOARD
});

export const receiveDashboard = (data) => ({
	type:RECEIVE_DASHBOARD,
	receivedAt: Date.now(),
	data
});

const fetchDashboard = () => dispatch => {
	log && console.log("before: dashboard request");
	dispatch(requestDashboard());
	return new Promise((resolve) => {
		setTimeout(() => {
			log && console.log("before: dashboard response");
			resolve(dashboard);
		}, 100);
	}).then(data => dispatch(receiveDashboard(data)));
};

const shouldFetchDashboard = (state) => {
	const db = state.dashboard;
	if(!db){
		return true;
	}
	if(db.isFetching){
		return false;
	}
	if(db.didInvalidate){
		return true;
	}
	return !db.data;
};

export const fetchDashboardIfNeeded = () => (dispatch, getState)=>{
	if(shouldFetchDashboard(getState())){
		return dispatch(fetchDashboard());
	}
};

