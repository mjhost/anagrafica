import React, { Component } from 'react';
import {Grid, Row, Col} from 'react-bootstrap';
import {HashRouter as Router, Route, Switch} from 'react-router-dom';

import './App.css';

import Header 			from 'layout/Header';
import Is404 			from 'layout/Is404';

import Dashboard 				from 'app/Dashboard';
import {Person, EditPerson} 	from 'app/person';

class App extends Component {

	render() {
		return (
			<Router>
				<div>
					<Header />
					<Grid>
						<Switch>
							<Route exact path="/" component={Dashboard} />
							<Route exact path="/person/new" component={EditPerson} />
							<Route path="/person/:id/edit" component={EditPerson} />
							<Route path="/person/:id" component={Person} />
{/*
							<Route path="/event/:id" component={EventDetails} />
							<Route path="/parish/:id" component={ParishDetails} />
							<Route path="/organization/:id" component={OrganizationDetails} />
*/}
							<Route component={Is404} />
						</Switch>
					</Grid>
					<footer>
						<Grid>
							<Row>
								<Col xs={12}>fine</Col>
							</Row>
						</Grid>
					</footer>
				</div>
			</Router>
		);
	}
}

export default App;
