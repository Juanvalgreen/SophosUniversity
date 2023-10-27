import { Component, } from '@angular/core';
import { ListingService } from 'src/app/services/listing/listing.service';

@Component({
  selector: 'app-listncreate',
  templateUrl: './listncreate.component.html',
  styleUrls: ['./listncreate.component.sass']
})
export class ListncreateComponent {

  currentList: string;

  constructor(private listingService: ListingService){

    this.currentList = '';
  }

  ngOnInit(){
    this.currentList =this.listingService.componentIndicate;

  }




}
