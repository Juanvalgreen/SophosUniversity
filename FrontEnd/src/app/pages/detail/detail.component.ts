import { Component } from '@angular/core';
import { DetailsDataService } from 'src/app/services/details-data/details-data.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.sass']
})
export class DetailComponent {


  constructor(private detailService: DetailsDataService) {

  }

  currentDetailObject: any;



  ngOnInit(){

    this.currentDetailObject = this.detailService.detailsData;



    console.log(this.currentDetailObject);

  }

}
