import React, { Component } from 'react';
import { Pagination } from 'semantic-ui-react';

export default class PaginationComponent extends Component {
  
  state = {
    boundaryRange: 1,
    siblingRange: 1,
    showEllipsis: true,
    showFirstAndLastNav: false,
    showPreviousAndNextNav: false
  }

  render() {
    const {
      boundaryRange,
      siblingRange,
      showEllipsis,
      showFirstAndLastNav,
      showPreviousAndNextNav,
    } = this.state;

    return (
        <Pagination
            activePage={this.props.pagination.number+1}
            boundaryRange={boundaryRange}
            onPageChange={this.props.onPageChange}
            size='small'
            siblingRange={siblingRange}
            totalPages={this.props.pagination.totalPages>0 ? this.props.pagination.totalPages : 1}
            ellipsisItem={showEllipsis ? undefined : null}
            firstItem={showFirstAndLastNav ? undefined : null}
            lastItem={showFirstAndLastNav ? undefined : null}
            prevItem={showPreviousAndNextNav ? undefined : null}
            nextItem={showPreviousAndNextNav ? undefined : null}
        />
    );
  }
}