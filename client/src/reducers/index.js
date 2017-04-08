import { combineReducers } from 'redux';
import { REQUEST_DASHBOARD, RECEIVE_DASHBOARD } from '../actions';

const defaultDashboard = {
	isFetching: false,
	didInvalidate: false
};

const dashboard = (state = defaultDashboard, action) => {
	switch(action.type){
		case REQUEST_DASHBOARD:
			return {
				...state,
				isFetching: true,
				didInvalidate: false
			};
		case RECEIVE_DASHBOARD:
			return {
				...state,
				isFetching: false,
				didInvalidate: false,
				data: action.data,
				lastUpdated: action.receivedAt
			};
		default:
			return state;
	}
};

const rootReducers = combineReducers({
	dashboard
});

export default rootReducers;