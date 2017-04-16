import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import {Button, Row, Col, Panel} from 'react-bootstrap';
import {Link} from 'react-router-dom';

import Loader from 'layout/Loader';

import {fetchPersonIfNeeded} from 'actions';


class Person extends React.Component{

	constructor(props){
		super(props);
		this.state = {
			ready:false
		};
	}

	componentDidMount(){
		const {dispatch, match} = this.props;
		dispatch(fetchPersonIfNeeded(match.params.id));
	}

	componentWillReceiveProps(nextProps){
		if(nextProps.match.params.id !== this.state.id){
			nextProps.dispatch(fetchPersonIfNeeded(nextProps.match.params.id));
		}
	}

	render (){
		if(this.props.isFetching || !this.props.data){
			return (<Loader/>);
		}
		let person = this.props.data;
		console.log("Person rendering with", this.props);
		return (
			<div>
				<Row>
					<Col md={6}>
						<h1>
							{person.name} {person.surname}
							{} <Button href={`#/person/${person.id}/edit`}>Modifica</Button>
						</h1>
					</Col>
				</Row>
				<Row>
					<Col md={8}>
					</Col>
					<Col md={4}>
						<Panel header="Parenti">
							<ul>
								{(person.relatives||[]).map(r=>{
									return (<li key={r.id}>
										<Link to={`/person/${r.id}`}>
											{r.name} {r.surname}
										</Link>
									</li>);
								})}
							</ul>
						</Panel>
					</Col>
				</Row>
			</div>
		);
	}
}

const mapStateToProps = (state, ownProps) => {
	// console.log(state, ownProps);
	let person = {isFetching:false, didInvalidate:true, ...state.persons.find(p=>(p.id === ownProps.match.params.id))};
	return person;
};

Person.propTypes = {
	match: PropTypes.shape({
		params: PropTypes.shape({
			id: PropTypes.oneOfType([
				PropTypes.string,
				PropTypes.number
			]).isRequired
		}).isRequired
	}).isRequired,
	dispatch: PropTypes.func.isRequired,
	isFetching: PropTypes.bool.isRequired,
	data: PropTypes.object,
	lastUpdated: PropTypes.number
};

export default connect(mapStateToProps)(Person);