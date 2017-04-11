import React from 'react';
import {Row, Col, Panel, FormControl, Button, FormGroup, InputGroup} from 'react-bootstrap';
import {Link} from 'react-router-dom';
import { connect } from 'react-redux';

import Loader from 'layout/Loader';

import {fetchDashboardIfNeeded} from 'actions';


class Dashboard extends React.Component {

	constructor(props){
		super(props);
		this.state = {
			ready:false
		};
	}

	componentDidMount(){
		const { dispatch } = this.props;
		dispatch(fetchDashboardIfNeeded());
	}

	render(){
		if(!this.props.isFetching && this.props.data){
			return (
				<div>
					<Row>
						<Col md={6}>
							<h2>Dashboard: 8 aprile 2017</h2>
						</Col>
						<Col md={6}>
							<FormGroup>
								<InputGroup>
									<FormControl bsSize="large" type="text" value='' placeholder="ricerca... "/>
									<InputGroup.Button>
										<Button bsSize="large">
											Cerca
										</Button>
									</InputGroup.Button>
								</InputGroup>
							</FormGroup>
						</Col>
					</Row>
					<Row>
						<Col md={4} xs={12}>
							<Panel header="Compleanni">
								<ul>
									{this.props.data.birthdays.map((item)=>(
										<li key={`bd${item.id}`}>
											<Link to={`/person/${item.id}`}>
												{item.name} {item.years && (
													<span>({item.years} anni)</span>
												)}
											</Link>
										</li>
									))}
								</ul>
							</Panel>
						</Col>
						<Col md={4} xs={12}>
							<Panel header="Matrimoni">
								<ul>
									{this.props.data.weddings.map(item=>(
										<li key={`w${item.husband.id}`}>
											<Link to={`/person/${item.husband.id}`}>
												{item.husband.name}
											</Link> e <Link to={`/person/${item.wife.id}`}>
												{item.wife.name}
											</Link> <span>({item.years} anni)</span>
										</li>
									))}
								</ul>
							</Panel>
						</Col>
						<Col md={4} xs={12}>
							<Panel header="Altri anniversari">
								<ul>
									{this.props.data.anniversaries.map(item=>(
										<li key={`a${item.id}`}>
											<Link to={`/person/${item.id}`}>
												{item.name}
											</Link> - <span>({item.event.relation} {item.event.name} {item.event.type})</span>
										</li>
									))}
								</ul>
							</Panel>
						</Col>
					</Row>
					<Row>
						<Col md={4} xs={12}>
							<Panel header="Eventi prossimi">
								<ul>
									{this.props.data.events.map(item=>(
										<li key={`a${item.id}`}>
											<Link to={`/event/${item.id}`}>
												{item.name}
											</Link> <span>({item.date})</span>
										</li>
									))}
								</ul>
							</Panel>
						</Col>
					</Row>
				</div>
			);
		}
		return (<Loader />);
	}

}

const mapStateToProps = state => {
	let dashboard = {isFetching:false, didInvalidate:true, ...state.dashboard};
	console.log("mapping to", dashboard);
	return {...dashboard};
};

Dashboard.propTypes = {
	dispatch: React.PropTypes.func.isRequired,
	isFetching: React.PropTypes.bool.isRequired,
	data: React.PropTypes.object,
	lastUpdated: React.PropTypes.number
};

export default connect(mapStateToProps)(Dashboard);

