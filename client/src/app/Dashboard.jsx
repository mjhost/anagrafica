import React from 'react';
import {Row, Col, Panel} from 'react-bootstrap';
import {Link} from 'react-router-dom';

import Loader from 'layout/Loader';

export default class Dashboard extends React.Component {

	constructor(props){
		super(props);
		this.state = {
			ready:false
		};
	}

	componentWillMount(){
		//load
		setTimeout(()=>{
			this.setState({ready: true});
		}, 500);
	}

	render(){
		if(this.state.ready){
			return (
				<div>
					<Row>
						<Col xs={4}>
							<Panel header="Toolbox">
								<ul>
									<li>
										<Link to="/person/new">Aggiungi persona</Link>
									</li>
								</ul>
							</Panel>
						</Col>
						<Col xs={4}>
							<h2>Eventi prossimi</h2>
						</Col>
						<Col xs={4}>
							<Panel header="Compleanni">
								<ul>
									<li>
										<Link to="/person/1">Tizio</Link>
									</li>
									<li>
										<Link to="/person/2">Caio</Link>
									</li>
									<li>
										<Link to="/person/3">Sempronio</Link>
									</li>
								</ul>
							</Panel>
						</Col>
						<Col xs={4}>
							<h2>Matrimoni</h2>
						</Col>
						<Col xs={4}>
							<h2>Altri anniversari</h2>
						</Col>
					</Row>
				</div>
			);
		}
		return (<Loader />);
	}


}
