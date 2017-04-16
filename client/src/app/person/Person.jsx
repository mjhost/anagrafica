import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import {Button, ButtonGroup, Row, Col, Panel} from 'react-bootstrap';
import {Link} from 'react-router-dom';

import Loader from 'layout/Loader';

import {fetchPersonIfNeeded, addToVisits} from 'actions';


class Person extends React.Component{

	constructor(props){
		super(props);
		this.state = {
			ready:false
		};
		this.markTodo = this.markTodo.bind(this);
	}

	markTodo(){
		const {dispatch} = this.props;
		dispatch(addToVisits(this.props.data));
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
				<div className="page-header">
					<ButtonGroup className="pull-right">
						<Button href={`#/person/${person.id}/edit`}>Modifica</Button>
						{!person.death && (
							<Button onClick={this.markTodo}>Visita oggi</Button>
						)}
					</ButtonGroup>
					<h1>
						{person.name} {person.surname}
					</h1>
				</div>
				<Row>
					<Col md={8}>
						<Panel>
							<dl className="dl-horizontal">
								{person.address && (
									<div>
										<dt>Indirizzo</dt>
										<dd><Link to={"streets/" + person.address.street.replace(/\s/g, "-")}>{person.address.street}, {person.address.number}</Link></dd>
									</div>
								)}
								<dt>Nascita</dt>
								<dd>{person.birthplace} {person.birth}</dd>
								{person.job && (
									<div>
										<dt>Lavoro</dt>
										<dd>{person.job.map(j=>(
												<span key={j}> <Link to={`/jobs/${j}`}>{j}</Link></span>
											))}
										</dd>
									</div>
								)}
								{person.hobby && (
									<div>
										<dt>Hobby</dt>
										<dd>{person.hobby.map(h=>(
												<span key={h}> <Link to={`/hobbies/${h}`}>{h}</Link></span>
											))}
										</dd>
									</div>
								)}
								{person.death && (
									<div>
										<dt>Morte</dt>
										<dd>{person.death}</dd>
									</div>
								)}
								<dt></dt>
								<dd></dd>
							</dl>
						</Panel>
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