import { combineReducers } from 'redux';
import { REQUEST_DASHBOARD, RECEIVE_DASHBOARD } from '../actions';
import { REQUEST_PERSON, RECEIVE_PERSON } from '../actions';
import { ADD_VISIT } from '../actions';

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

const persons = (state = [], action) => {
	let listWithoutThisPerson = state.slice(0).filter(p=>(p.id !== action.id));
	switch (action.type) {
		case REQUEST_PERSON:
			listWithoutThisPerson.push({
				id: action.id,
				isFetching: true,
				didInvalidate: false
			});
			return listWithoutThisPerson;
		case RECEIVE_PERSON:
			listWithoutThisPerson.push({
				id: action.id,
				isFetching: false,
				didInvalidate: false,
				data: action.data,
				lastUpdated: action.receivedAt
			});
			return listWithoutThisPerson;
		default:
			return state;
	}
};

const visits = (state = [], action) => {
	switch (action.type) {
		case ADD_VISIT:
			if(state.some(p=>(p.id === action.person.id))){
				return state;
			}
			return state.concat(action.person);
		default:
			return state;
	}
};

const rootReducers = combineReducers({
	dashboard,
	persons,
	visits
});

export default rootReducers;