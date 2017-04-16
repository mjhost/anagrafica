import Promise from 'promise';
import * as mocks from './mocks';

const log = true;

export const REQUEST_PERSON = 'REQUEST_PERSON';
export const RECEIVE_PERSON = 'RECEIVE_PERSON';

export const requestPerson = (id) => ({
	type: REQUEST_PERSON,
	id
});

export const receivePerson = (id, data) => ({
	type:RECEIVE_PERSON,
	receivedAt: Date.now(),
	id,
	data
});

const fetchPerson = (id) => dispatch => {
	log && console.log("before: person request");
	dispatch(requestPerson(id));
	return new Promise((resolve) => {
		setTimeout(() => {
			log && console.log("before: person response");
			resolve(mocks["persona_" + id]);
		}, 100);
	}).then(data => dispatch(receivePerson(id, {id, ...data})));
};

const shouldFetchPerson = (id, state) => {
	const db = (state.persons || []).find((p)=>(p.id === id));
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

export const fetchPersonIfNeeded = (id) => (dispatch, getState)=>{
	if(shouldFetchPerson(id, getState())){
		return dispatch(fetchPerson(id));
	}
};

