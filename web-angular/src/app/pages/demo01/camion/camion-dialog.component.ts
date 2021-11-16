import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { CamionFilter } from 'src/app/core/model/base-model.model';
import { EntityFilterService } from 'src/app/core/service/baseentity.service';
import { BaseNavRecord } from 'src/app/core/util/base-nav-record';

@Component({
  selector: 'app-camion-dialog',
  templateUrl: './camion-dialog.component.html',
  styleUrls: ['./camion-dialog.component.scss']
})
export class CamionDialogComponent extends BaseNavRecord<CamionFilter, number> implements OnInit {

  formFilter: FormGroup;

  filterCliente: string;

  filterPlaca: string;
  
  filterNumguia: string;

  constructor(private serviceFilter: EntityFilterService, private formBuilder: FormBuilder, 
    public modal: NgbActiveModal
  ) { 
    super();
  }

  ngOnInit(): void {
    super.ngOnInit();

    this.formFilter = this.formBuilder.group({
      "filterCliente": ['', Validators.nullValidator],
      "filterPlaca": ['', Validators.nullValidator],
      "filterNumguia": ['', Validators.nullValidator],
    });
  }

  doLoad(): void {
    this.rows = [];
    this.serviceFilter.getCamiones(0, this.filterCliente, this.filterPlaca, this.filterNumguia).subscribe(
      res => this.rows = res
    );
  }

}
